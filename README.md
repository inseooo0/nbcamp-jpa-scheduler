## API 명세서

## schedule API
### schedule 등록
- request
  - method: post
  - uri: /api/schedule
  - request body:
    ```
    {
      "title": "string",
      "content": "string",
      "userId": 0
    }
    ```
- response
  - status code: 200
  - response body:
    ```
    {
      "id": 0,
      "title": "string",
      "content": "string",
      "createAt": "2024-08-13T11:36:19",
      "updateAt": "2024-08-13T11:36:19",
      "user": {
        "id": 0,
        "name": "string",
        "email": "string",
        "createAt": "2024-08-13T04:31:35",
        "updateAt": "2024-08-13T04:31:35"
      }
    }
    ```
### schedule 단건 조회
- request
  - method: get
  - uri: /api/schedule/{schedulId}
  - parameters: (path) scheduleId
- response
  - status code: 200
  - response body:
    ```
    {
      "id": 0,
      "title": "string",
      "content": "string",
      "createAt": "2024-08-13T11:36:19",
      "updateAt": "2024-08-13T11:36:19",
      "user": {
        "id": 0,
        "name": "string",
        "email": "string",
        "createAt": "2024-08-13T04:31:35",
        "updateAt": "2024-08-13T04:31:35"
      }
    }
    ```
- 존재하지 않는 일정 Id를 조회했을 경우
  - status code: 400 
  - response body:
    ```
    {
      "code": "INVALID_PARAMETER",
      "message": "Invalid parameter included"
    }
    ```
### schedule 전체 조회
- request
  - method: get
  - uri: /api/schedule
  - parameters:
    - (query) pageNum: 페이지 번호 (required)
    - (query) pageSize: 페이지 크기
- response
  - status code: 200
  - response body:
    ```
    [
      {
        "id": 0,
        "title": "string",
        "content": "string",
        "createAt": "2024-08-13T04:35:12",
        "updateAt": "2024-08-13T04:35:12"
      },
      ...
    ]
    ```
- request
  - method: get
  - uri: /api/schedule/{schedulId}
  - parameters: (path) scheduleId
- response
  - status code: 200
  - response body:
    ```
    {
      "id": 0,
      "title": "string",
      "content": "string",
      "createAt": "2024-08-13T11:36:19",
      "updateAt": "2024-08-13T11:36:19",
      "user": {
        "id": 0,
        "name": "string",
        "email": "string",
        "createAt": "2024-08-13T04:31:35",
        "updateAt": "2024-08-13T04:31:35"
      }
    }
    ```
### schedule 수정
- request
  - method: put
  - uri: /api/schedule/{scheduleId}
  - parameters: (path) scheduleId
  - request body:
    ```
    {
      "title": "string",
      "content": "string",
      "userId": "string"
    }
    ```
- response
  - status code: 200
  - response body:
    ```
    {
      "id": 0,
      "title": "string",
      "content": "string",
      "createAt": "2024-08-13T11:36:19",
      "updateAt": "2024-08-13T11:36:19",
      "user": {
        "id": 0,
        "name": "string",
        "email": "string",
        "createAt": "2024-08-13T04:31:35",
        "updateAt": "2024-08-13T04:31:35"
      }
    }
    ```
### schedule 삭제
- request
  - method: delete
  - uri: /api/schedule/{scheduleId}
  - parameters: (path) scheduleId
  
- response
  - status code: 200
  - response body:
    ```
    "ok"
    ```
- 존재하지 않는 일정 Id를 입력했을 경우
  - status code: 400 
  - response body:
    ```
    {
      "code": "INVALID_PARAMETER",
      "message": "Invalid parameter included"
    }
    ```

## comment API
### comment 등록
- request
  - method: post
  - uri: /api/comments
  - request body:
    ```
    {
      "scheduleId": 0,
      "userId": 0,
      "content": "string"
    }
    ```
- response
  - status code: 200
  - response body:
    ```
    {
      "id": 0,
      "scheduleId": 0,
      "content": "string",
      "createAt": "2024-08-13T11:36:19",
      "updateAt": "2024-08-13T11:36:19",
      "user": {
        "id": 0,
        "name": "string",
        "email": "string",
        "createAt": "2024-08-13T04:31:35",
        "updateAt": "2024-08-13T04:31:35"
      }
    }
    ```
### comment 단건 조회
- request
  - method: get
  - uri: /api/comments/{commentId}
  - parameters: (path) commentId
- response
  - status code: 200
  - response body:
    ```
    {
      "id": 0,
      "scheduleId": 0,
      "content": "string",
      "createAt": "2024-08-13T11:36:19",
      "updateAt": "2024-08-13T11:36:19",
      "user": {
        "id": 0,
        "name": "string",
        "email": "string",
        "createAt": "2024-08-13T04:31:35",
        "updateAt": "2024-08-13T04:31:35"
      }
    }
    ```
### comment 전체 조회
- request
  - method: get
  - uri: /api/comments
- response
  - status code: 200
  - response body:
    ```
    [
      {
      "id": 0,
      "scheduleId": 0,
      "content": "string",
      "createAt": "2024-08-13T11:36:19",
      "updateAt": "2024-08-13T11:36:19",
      "user": {
        "id": 0,
        "name": "string",
        "email": "string",
        "createAt": "2024-08-13T04:31:35",
        "updateAt": "2024-08-13T04:31:35"
        }
      },
      ...
    ]
    ```
### comment 수정
- request
  - method: put
  - uri: /api/comments/{commentId}
  - parameters: (path) commentId
  - request body:
    ```
    {
      "content": "string"
    }
    ```
- response
  - status code: 200
  - response body:
    ```
    {
      "id": 0,
      "scheduleId": 0,
      "content": "string",
      "createAt": "2024-08-13T11:36:19",
      "updateAt": "2024-08-13T11:36:19",
      "user": {
        "id": 0,
        "name": "string",
        "email": "string",
        "createAt": "2024-08-13T04:31:35",
        "updateAt": "2024-08-13T04:31:35"
      }
    }
    ```
### comment 삭제
- request
  - method: delete
  - uri: /api/comments/{commentId}
  - parameters: (path) commentId
    
- response
  - status code: 200
  - response body:
    ```
    "ok"
    ```
- 존재하지 않는 댓글 Id를 입력했을 경우
  - status code: 400 
  - response body:
    ```
    {
      "code": "INVALID_PARAMETER",
      "message": "Invalid parameter included"
    }
    ```
## user API
### user 등록
- request
  - method: post
  - uri: /api/users
  - request body:
    ```
    {
      "name": "string",
      "email": "string"
    }
    ```
- response
  - status code: 200
  - response body:
    ```
    {
      "id": 0,
      "name": "string",
      "email": "string",
      "createAt": "2024-08-13T04:31:35",
      "updateAt": "2024-08-13T04:31:35"
    }
    ```
### user 단건 조회
- request
  - method: get
  - uri: /api/users/{userId}
  - parameters: (path) userId
- response
  - status code: 200
  - response body:
    ```
    {
      "id": 0,
      "name": "string",
      "email": "string",
      "createAt": "2024-08-13T04:31:35",
      "updateAt": "2024-08-13T04:31:35"
    }
    ```
### user 전체 조회
- request
  - method: get
  - uri: /api/users
- response
  - status code: 200
  - response body:
    ```
    [
      {
      "id": 0,
      "name": "string",
      "email": "string",
      "createAt": "2024-08-13T04:31:35",
      "updateAt": "2024-08-13T04:31:35"
      },
      ...
    ]
    ```
### user 수정
- request
  - method: put
  - uri: /api/users/{userId}
  - parameters: (path) userId
  - request body:
    ```
    {
      "name": "string",
      "email": "string"
    }
    ```
- response
  - status code: 200
  - response body:
    ```
    {
      "id": 0,
      "name": "string",
      "email": "string",
      "createAt": "2024-08-13T04:31:35",
      "updateAt": "2024-08-13T04:31:35"
    }
    ```
### user 삭제
- request
  - method: delete
  - uri: /api/users/{userId}
  - parameters: (path) userId
    
- response
  - status code: 200
  - response body:
    ```
    "ok"
    ```
- 존재하지 않는 유저 Id를 입력했을 경우
  - status code: 400 
  - response body:
    ```
    {
      "code": "INVALID_PARAMETER",
      "message": "Invalid parameter included"
    }
    ```

## ERD
![image](https://github.com/user-attachments/assets/98f314a9-ba97-456f-a48c-6b37b8026951)

### schedule table
|column name|type|description|기본값|null 허용|
|---|---|---|---|---|
|id|BIGINT|일정 ID (PK)|auto_increment|X|
|title|VARCHAR(200)|일정 제목|X|X|
|content|VARCHAR(200)|일정 내용|X|X|
|user_id|BIGINT|작성 유저 ID (FK)|X|X|
|create_at|DATETIME|일정 등록일|CURRENT_TIMESTAMP|X|
|update_at|DATETIME|일정 수정일|CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP|X|

### user table
|column name|type|description|기본값|null 허용|
|---|---|---|---|---|
|id|BIGINT|유저 ID (PK)|auto_increment|X|
|name|VARCHAR(200)|유저 이름|X|X|
|email|VARCHAR(200)|유저 email|X|X|
|create_at|DATETIME|유저 정보 등록일|CURRENT_TIMESTAMP|X|
|update_at|DATETIME|유저 정보 수정일|CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP|X|

### comment table
|column name|type|description|기본값|null 허용|
|---|---|---|---|---|
|id|BIGINT|댓글 ID (PK)|auto_increment|X|
|schedule_id|BIGINT|일정 ID (FK)|X|X|
|user_id|BIGINT|작성 유저 ID (FK)|X|X|
|content|VARCHAR(200)|댓글 내용|X|X|
|create_at|DATETIME|댓글 등록일|CURRENT_TIMESTAMP|X|
|update_at|DATETIME|댓글 수정일|CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP|X|
