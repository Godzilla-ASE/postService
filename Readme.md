# Post Service

Post Service is a backend service that handles posts-related operations. 
Including:
- Create posts/comments/replies
- Like/Unlike posts
- Delete posts/comments/replies
- Get posts through postid/userid/tag/location 
- Get all posts
- Get like users of a post
- Modify posts
- Get comments and replies through postid/comment id

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Maven

## Configuration

1. Open the `application.properties` file in the `src/main/resources` directory.
2. Provide the necessary configuration values, such as database connection details.

## Usage

1. Start the User Service by running the following command:

```bash
./mvnw spring-boot:run
```

2. The service will be accessible at `http://localhost:8082`.

## API Endpoints

The User Service provides the following API endpoints:

- `GET /posts`: Get all posts.
- `GET /posts/users/{id}`: Get posts by user id.
- `GET /posts/likedUsers/{id}`: Get all liked users of a post.
- `POST /posts`: Create a new post.
- `POST /posts/location`: Get posts by location
- `GET /posts/tags/{tag}`: Get posts by tags
- `PUT /posts/attitude`: Like/Unlike a post
- `PUT /posts/{id}`: Update a post.
- `DELETE /posts/{id}`: Delete a post.
- `POST /comments`: Create a comment.
- `POST /comments/reply`: Create a reply.
- `GET /comments/{id}`: Get comments and replies by post id
- `DELETE /comments/{id}`: Delete comments by comment id

More details can be found in https://docs.google.com/spreadsheets/d/1nK-1bhyr9BWeO70jgLF9rlERT9HntIfBY5a4Xjk5ZaI/edit#gid=0  

## Dependency

Post service depends on user service, and mysql service.

## Testing and continuous integration

## Author

ASE Godzilla group : Manyi Wang
