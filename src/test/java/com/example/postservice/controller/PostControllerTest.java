//package com.example.postservice.controller;
//
//import com.example.postservice.DTOMapping.dto.PostPostDTO;
//import com.example.postservice.entity.Post;
//import com.example.postservice.service.PostService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.server.ResponseStatusException;
//
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.doNothing;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public class PostControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private PostService postService;
//
//    @MockBean
//    private RestTemplate restTemplate;
//
//    @Test
//    public void cratePost_succeed() throws Exception {
//        PostPostDTO postPostDTO = new PostPostDTO();
//        postPostDTO.setContent_img("url");
//        postPostDTO.setContent_text("test");
//        postPostDTO.setCoverImage("url");
//        postPostDTO.setTitle("title");
//        postPostDTO.setUserid(1);
//
//        Post post = new Post();
//        post.setContent_img("url");
//        post.setContent_text("test");
//        post.setCoverImage("url");
//        post.setTitle("title");
//        post.setUserid(1);
//
//        given(postService.createPost(Mockito.any())).willReturn(post);
//
//        MockHttpServletRequestBuilder postRequest = post("/posts")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(postPostDTO)).header(HttpHeaders.AUTHORIZATION,"12345678910");
//
//        mockMvc.perform(postRequest)
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    public void deletePost_succeed() throws Exception{
//        doNothing().when(postService).deletePost(Mockito.anyInt());
//        MockHttpServletRequestBuilder deleteRequest = delete("/posts/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{}").header(HttpHeaders.AUTHORIZATION,"12345678910");
//
//        mockMvc.perform(deleteRequest)
//                .andExpect(status().isNoContent());
//    }
//
////    @Test
////    public void getPostById_succeed() throws Exception{
////        Post post = new Post();
////        post.setContent_img("url");
////        post.setContent_text("test");
////        post.setCoverImage("url");
////        post.setTitle("title");
////        post.setUserid(1);
////
////
////    }
//
//    private String asJsonString(final Object object) {
//        try {
//            return new ObjectMapper().writeValueAsString(object);
//        } catch (JsonProcessingException e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    String.format("The request body could not be created.%s", e.toString()));
//        }
//    }
//
//}
