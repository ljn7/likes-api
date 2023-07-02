package com.service.likes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.helper.UserIdAndContentId;
import com.service.likes.model.Like;
import com.service.likes.model.Notification;
import com.service.likes.service.ContentService;
import com.service.likes.service.LikeService;
import com.service.likes.service.NotificationService;
import com.service.likes.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/likes")
public class LikesController {

    @Autowired
    private LikeService likeService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private UserService userService;
    @Autowired
    NotificationService notificationService;

    HttpHeaders headers = new HttpHeaders();

    @Operation(summary = "Root directory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json")) })
    @GetMapping(path = "/")
    public String hello(
            @Parameter(description = "test string") @RequestParam(required = false, defaultValue = "World") String test) {
        return String.format("Hello %s", test);
    }

    @Operation(summary = "Like the content")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Already Liked"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "403", description = "User or Content Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error") })
    @PostMapping(path = "/like")
    public ResponseEntity<Like> likeContent(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
                content = @Content(schema = @Schema(implementation = UserIdAndContentId.class))) @RequestBody Map<String, Long> requestBody) {

        Long userId = requestBody.get("userId");
        Long contentId = requestBody.get("contentId");

        if (userId == null || contentId == null) {
            return ResponseEntity.badRequest().build();
        }
        if (userService.findUserById(userId) == null) {
            return ResponseEntity.notFound().build();
        }
        com.service.likes.model.Content content = contentService.findByContentId(contentId);

        if (content == null) {
            return ResponseEntity.notFound().build();
        }
        if (contentService.findByUserId(userId) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Long totalLikes = content.getTotalLikes() + 1;
        content.setTotalLikes(totalLikes);
        contentService.saveContent(content);

        if (totalLikes >= 100) {
            Notification notification = new Notification();
            notification.setUserId(content.getUserId());
            notification.setMessage("Your content received more than 99 likes!");
            notificationService.saveNotification(notification);

        }

        Map<Like, HttpStatus> result = likeService.likeContent(userId, contentId);
        Like like = result.keySet().iterator().next();
        HttpStatus statusCode = result.get(like);

        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(like, headers, statusCode);

    }

    @Operation(summary = "Checks the content if is it liked by given user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "403", description = "User or Content Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error") })
    @GetMapping(path = "/isliked")
    public ResponseEntity<Like> liked(@Parameter(description = "Valid User ID") @RequestParam Long userId,
        @Parameter(description = "Valid content ID") @RequestParam Long contentId) {

        if (userId == null || contentId == null) {
            return ResponseEntity.badRequest().build();
        }
        if (userService.findUserById(userId) == null) {
            return ResponseEntity.notFound().build();
        }

        if (contentService.findByContentId(contentId) == null) {
            return ResponseEntity.notFound().build();
        }

        Map<Like, HttpStatus> result = likeService.isLiked(userId, contentId);
        Like like = result.keySet().iterator().next();
        HttpStatus statusCode = result.get(like);

        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(like, headers, statusCode);

    }

    @Operation(summary = "Returns total number of likes of content")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "403", description = "Content Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error") })
    @GetMapping(path = "/total-likes")
    public ResponseEntity<Map<String, Long>> getTotalLikes(@Parameter(description = "Valid Content ID") @RequestParam Long contentId) {

        if (contentId == null) {
            return ResponseEntity.badRequest().build();
        }
        if (contentService.findByContentId(contentId) == null) {
            return ResponseEntity.notFound().build();
        }
        Map<Long, HttpStatus> result = contentService.getTotalLike(contentId);
        Long res = result.keySet().iterator().next();
        HttpStatus statusCode = result.get(res);

        Map<String, Long> response = new HashMap<>();
        response.put("Total Likes", res);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(response, headers, statusCode);

    }

}
