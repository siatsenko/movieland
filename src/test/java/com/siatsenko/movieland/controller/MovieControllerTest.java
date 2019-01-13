package com.siatsenko.movieland.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.siatsenko.movieland.entity.LoginRequest;
import com.siatsenko.movieland.entity.ReviewRequest;
import com.siatsenko.movieland.entity.Role;
import com.siatsenko.movieland.entity.Session;
import com.siatsenko.movieland.interceptor.AuthInterceptor;
import com.siatsenko.movieland.service.MovieService;
import com.siatsenko.movieland.service.RequestParamsService;

import com.siatsenko.movieland.web.UserHandler;
import com.siatsenko.movieland.web.annotation.ProtectedBy;
import com.siatsenko.movieland.web.controller.AuthController;
import com.siatsenko.movieland.web.controller.MovieController;
import com.siatsenko.movieland.web.controller.ReviewController;
import com.siatsenko.movieland.web.controller.util.DtoConverter;
import com.siatsenko.movieland.web.dto.LoginUserDto;
import com.siatsenko.movieland.web.dto.MovieDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@Configuration
//public class ApplicationConfigurerAdapter implements WebMvcConfigurer  {
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authInterceptor); //.addPathPatterns("/**").excludePathPatterns("/admin/**");
//    }
//
//}

@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(value = "/spring/test-context.xml")
@ContextConfiguration(value = "/spring/test-context.xml")
//locations = { "classpath*:META-INF/spring.xml", ... }
public class MovieControllerTest  implements WebMvcConfigurer{
    AuthController authController;
    ReviewController reviewController;
    MovieController movieController;
    DtoConverter dtoConverter;
    RequestParamsService requestParamsService;
    MovieService movieService;
    AuthInterceptor authInterceptor;

    @Autowired
    ApplicationContext context;

    MockMvc mockAuthController;
    MockMvc mockReviewController;
    MockMvc mockMovieController;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor); //.addPathPatterns("/**").excludePathPatterns("/admin/**");
    }

    @Before
    public void before() {
        mockAuthController = MockMvcBuilders.standaloneSetup(authController).build();
        mockReviewController = MockMvcBuilders.standaloneSetup(reviewController).build();
        mockMovieController = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    public void iGetById() throws Exception {

//        context.containsBean()

//    AuthInterceptor authInterceptor = (AuthInterceptor) context.getBean(Class.forName("com.siatsenko.movieland.interceptor.AuthInterceptor",false,this.getClass().getClassLoader()));
//        System.out.println(authInterceptor);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("ronald.reynolds66@example.com");
        loginRequest.setPassword("paco");

        ObjectMapper mapper = new ObjectMapper();
        String loginRequestJson=mapper.writeValueAsString(loginRequest);

        MvcResult mvcAuthResult =
                mockAuthController.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .content(loginRequestJson))
                        .andExpect(status().isOk())
                        .andReturn();

        String loginResponse = mvcAuthResult.getResponse().getContentAsString();
        LoginUserDto loginUserDto = mapper.readValue(loginResponse, LoginUserDto.class);
//        assertEquals("Рональд Рейнольдс",loginUserDto.getNickname());

        String uuid = loginUserDto.getUuid();


        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setMovieId(1);
        reviewRequest.setText("Cool!");

        String reviewRequestJson=mapper.writeValueAsString(reviewRequest);


//        MvcResult mvcReviewResult =
//                mockReviewController.perform(MockMvcRequestBuilders.post("/review")
//                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                        .header("uuid",uuid)
//                        .content(reviewRequestJson))
//                        .andExpect(status().isOk());

//        @ProtectedBy(acceptedRole = Role.USER)
//        @PostMapping(path = "/review", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//        public void add(@RequestBody ReviewRequest reviewRequest) {
//            reviewService.add(reviewRequest, UserHandler.getCurrentUser());
//            logger.debug("add {}:", UserHandler.getCurrentUser());
//        }

    }


    @Test
    public void getAll() {

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("rating", "desc");
        queryMap.put("price", "asc");

        List<MovieDto> movieDtos = movieController.getAll(queryMap);

        assertEquals(4, movieDtos.size());

        MovieDto movieDtoFirst = movieDtos.get(0);
        assertEquals(4, movieDtoFirst.getId());
//        assertEquals("Танцующий с волками", movieDtoFirst.getNameRussian());
        assertEquals("Dances with Wolves", movieDtoFirst.getNameNative());
        assertEquals("1990", movieDtoFirst.getYearOfRelease());
        assertEquals(8.00, movieDtoFirst.getRating(), 0.000001);
        assertEquals(120.55, movieDtoFirst.getPrice(), 0.000001);
        assertEquals("https://images-na.ssl-images-amazon.com/images/M/MV5BMTY3OTI5NDczN15BMl5BanBnXkFtZTcwNDA0NDY3Mw@@._V1._SX140_CR0,0,140,209_.jpg", movieDtoFirst.getPicturePath());

        MovieDto movieDtoSecond = movieDtos.get(1);
        assertEquals(1, movieDtoSecond.getId());
//        assertEquals("Побег из Шоушенка", movieDtoSecond.getNameRussian());
        assertEquals("The Shawshank Redemption", movieDtoSecond.getNameNative());
        assertEquals("1994", movieDtoSecond.getYearOfRelease());
        assertEquals(8.9, movieDtoSecond.getRating(), 0.000001);
        assertEquals(123.45, movieDtoSecond.getPrice(), 0.000001);
        assertEquals("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg", movieDtoSecond.getPicturePath());

    }

    @Test
    public void getRandom() {

        List<MovieDto> movieDtos = movieController.getRandom();

        assertEquals(2, movieDtos.size());

    }

    @Test
    public void getByGenreId() {

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("rating", "asc");

        List<MovieDto> movieDtos = movieController.getByGenreId(2, queryMap);

        assertEquals(2, movieDtos.size());

        MovieDto movieDtoFirst = movieDtos.get(0);
        assertEquals(1, movieDtoFirst.getId());
//        assertEquals("Побег из Шоушенка", movieDtoFirst.getNameRussian());
        assertEquals("The Shawshank Redemption", movieDtoFirst.getNameNative());
        assertEquals("1994", movieDtoFirst.getYearOfRelease());
        assertEquals(8.9, movieDtoFirst.getRating(), 0.000001);
        assertEquals(123.45, movieDtoFirst.getPrice(), 0.000001);
        assertEquals("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg", movieDtoFirst.getPicturePath());

        MovieDto movieDtoSecond = movieDtos.get(1);
        assertEquals(2, movieDtoSecond.getId());
//        assertEquals("Зеленая миля", movieDtoSecond.getNameRussian());
        assertEquals("The Green Mile", movieDtoSecond.getNameNative());
        assertEquals("1999", movieDtoSecond.getYearOfRelease());
        assertEquals(8.9, movieDtoSecond.getRating(), 0.000001);
        assertEquals(134.67, movieDtoSecond.getPrice(), 0.000001);
        assertEquals("https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxMzQyNjA5MF5BMl5BanBnXkFtZTYwOTU2NTY3._V1._SY209_CR0,0,140,209_.jpg", movieDtoSecond.getPicturePath());

    }

//    @Autowired
//    public void setXmlWebApplicationContext(XmlWebApplicationContext xmlWebApplicationContext) {
//        this.xmlWebApplicationContext = xmlWebApplicationContext;
//    }


    @Autowired
    public void setAuthController(AuthController authController) {
        this.authController = authController;
    }

    @Autowired
    public void setMovieController(MovieController movieController) {
        this.movieController = movieController;
    }

    @Autowired
    public void setReviewController(ReviewController reviewController) {
        this.reviewController = reviewController;
    }

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @Autowired
    public void setDtoConverter(DtoConverter dtoConverter) {
        this.dtoConverter = dtoConverter;
    }

    @Autowired
    public void setAuthInterceptor(AuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }

    @Autowired
    public void setRequestParamsService(RequestParamsService requestParamsService) {
        this.requestParamsService = requestParamsService;
    }

}