package com.siatsenko.movieland.service.impl.report;

import com.siatsenko.movieland.entity.report.Report;
import com.siatsenko.movieland.service.ReportUploaderService;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@Service
public class DefaultReportUploaderService implements ReportUploaderService {

    @Value("${reports.ftp.url}")
    private String url;
    @Value("${reports.ftp.port}")
    private String port;
    @Value("${reports.ftp.username}")
    private String userName;
    @Value("${reports.ftp.password}")
    private String password;

    @Override
    public boolean upload(Report report) {
        boolean result = false;
        String inputFilename = report.getFile();
        String filename = Paths.get(inputFilename).getFileName().toString();

        FTPClient client = new FTPClient();
        try (InputStream is = new FileInputStream(inputFilename)) {
            client.connect(url, Integer.parseInt(port));
            client.login(userName, password);
            client.enterLocalPassiveMode();
            client.setFileType(FTP.BINARY_FILE_TYPE);
            result = client.storeFile(filename, is);
            client.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (result) {
            String fullUrl = url + ":" + port;
            report.setLink("ftp://" + fullUrl + "/" + filename);
        }
        return result;
    }

//    private ftpUpload(String fileName)
}
