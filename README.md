# scheduler-ver2
일정관리 어플리케이션 ver2 입니다.

추가된 항목들
- JDBC Template ->  JPA로 변경
- Cookie/Session 이용한 로그인
- 댓글 기능

---

## 유저 API

<details>
<summary>유저 생성</summary>
<div markdown="1">


| **이름**   | **타입**               | **설명**  | **필수여부** |
|----------|----------------------|---------| --- |
| id       | INT (AUTO_INCREMENT) | 유저 id   | Y |
| name     | VARCHAR(10)          | 유저 이름   | Y |
| email    | VARCHAR(40)          | 유저 이메일  | Y |
| password | VARCHAR(255)         | 유저 이메일  | Y |

### Request

```
POST 'localhost:8080/users/signup'
```

```json
{
  "name": "Park",
  "email": "bbb@gmail.com",
  "password": "1234"
}
```

### Response

Success HTTP Status : 201

``` json
{
   "id": 1,
    "name": "Park",
    "email": "bbb@gmail.com"
}

```

</div>
</details>

<details>
<summary>유저 전체 조회</summary>
<div markdown="1">


| **이름**   | **타입**               | **설명**  | **필수여부** |
|----------|----------------------|---------| --- |
| id       | INT (AUTO_INCREMENT) | 유저 id   | Y |
| name     | VARCHAR(10)          | 유저 이름   | Y |
| email    | VARCHAR(40)          | 유저 이메일  | Y |

### Request

```
GET 'localhost:8080/users'
```

### Response

Success HTTP Status : 200

``` json
[
    {
        "id": 1,
        "name": "Park",
        "email": "bbb@gmail.com"
    },
    {
        "id": 2,
        "name": "Kim",
        "email": "kim@gmail.com"
    }
]
```

유저가 없는 경우 빈 배열 반환

``` json
[]
```

</div>
</details>

<details>
<summary>특정 유저 조회</summary>
<div markdown="1">


| **이름**   | **타입**               | **설명**  | **필수여부** |
|----------|----------------------|---------| --- |
| id       | INT (AUTO_INCREMENT) | 유저 id   | Y |
| name     | VARCHAR(10)          | 유저 이름   | Y |
| email    | VARCHAR(40)          | 유저 이메일  | Y |

### Request

```
GET 'localhost:8080/users/{id}'
```

### Response

Success HTTP Status : 200

Fail HTTP Status : 404

성공

``` json
{
    "id": 1,
    "name": "Park",
    "email": "bbb@gmail.com"
}
```

실패

``` json
{
    "timestamp": "2024-11-15T03:09:07.588+00:00",
    "status": 404,
    "error": "Not Found",
    "message": "해당 유저를 찾을 수 없습니다.",
    "path": "/users/4"
}
```

</div>
</details>

<details>
<summary>유저 삭제</summary>
<div markdown="1">

### Request

```
DELETE 'localhost:8080/users/{id}'
```

### Response

Success HTTP Status : 200

Fail HTTP Status : 404

성공

``` plaintext

```

실패

``` json
{
    "timestamp": "2024-11-15T03:11:21.422+00:00",
    "status": 404,
    "error": "Not Found",
    "message": "해당 유저를 찾을 수 없습니다.",
    "path": "/users/5"
}
```

</div>
</details>

<details>
<summary>로그인</summary>
<div markdown="1">


| **이름**   | **타입**       | **설명**  | **필수여부** |
|----------|--------------|---------| --- |
| name     | VARCHAR(10)  | 유저 이름   | Y |
| email    | VARCHAR(40)  | 유저 이메일  | Y |
| password | VARCHAR(255) | 유저 비밀번호 | Y |

### Request

```
POST 'localhost:8080/login'
```

```json
{
    "email": "bbb@gmail.com",
    "password": "1234"
}
```

### Response

Success HTTP Status : 200

Fail HTTP Status : 401

성공

``` json
{
    "name": "Park",
    "email": "bbb@gmail.com",
    "responseMessage": "로그인 성공 !"
}
```

실패

``` json
{
    "timestamp": "2024-11-15T03:15:58.696+00:00",
    "status": 401,
    "error": "Unauthorized",
    "message": "잘못된 이메일 입니다.",
    "path": "/login"
}
```

``` json
{
    "timestamp": "2024-11-15T03:17:09.684+00:00",
    "status": 401,
    "error": "Unauthorized",
    "message": "비밀번호가 일치하지 않습니다",
    "path": "/login"
}
```

</div>
</details>

<details>
<summary>로그아웃</summary>
<div markdown="1">

### Request

```
POST 'localhost:8080/logout'
```

### Response

Success HTTP Status : 200

```
로그아웃 성공
```

</div>
</details>

## 일정 API

<details>
<summary>전체 일정 조회</summary>
<div markdown="1">


| **이름**   | **타입**               | **설명** | **필수여부** |
|----------|----------------------|--------| --- |
| id       | INT (AUTO_INCREMENT) | 일정 id  | Y |
| title    | VARCHAR(200)         | 일정 제목  | Y |
| contents | VARCHAR(200)         | 일정 내용  | Y |
| username | VARCHAR(40)          | 유저 이름  | Y |

### Request

```
GET 'localhost:8080/schedules'
```

### Response

Success HTTP Status : 200

데이터가 없으면 빈 배열을 반환합니다.

성공

``` json
[
    {
        "id": 1,
        "title": "일정1",
        "contents": "내용1",
        "username": "Kim"
    },
    {
        "id": 2,
        "title": "일정2",
        "contents": "내용2",
        "username": "Kim"
    },
    {
        "id": 3,
        "title": "일정3",
        "contents": "내용3",
        "username": "Kim"
    },
    {
        "id": 4,
        "title": "스파르타",
        "contents": "스프링",
        "username": "Kim"
    },
    {
        "id": 5,
        "title": "스파르타",
        "contents": "스프링",
        "username": "Park"
    }
]
```

데이터가 없는 경우

``` json
{
    []
}

 ```

</div>
</details>

<details>
<summary>선택 일정 조회</summary>
<div markdown="1">

| **이름**   | **타입**               | **설명** | **필수여부** |
|----------|----------------------|--------| --- |
| id       | INT (AUTO_INCREMENT) | 일정 id  | Y |
| title    | VARCHAR(200)         | 일정 제목  | Y |
| contents | VARCHAR(200)         | 일정 내용  | Y |
| username | VARCHAR(40)          | 유저 이름  | Y |

### Request

id로 일정을 조회합니다.

```
GET 'localhost:8080/schedules/{id}'
```

### Response

단일 일정 목록

Success HTTP Status : 200

Fail HTTP Status : 404

성공

``` json
{
    "id": 1,
    "title": "일정1",
    "contents": "내용1",
    "username": "Kim"
}
 ```

실패

``` json
{
    "timestamp": "2024-11-15T03:44:37.177+00:00",
    "status": 404,
    "error": "Not Found",
    "message": "해당 일정을 찾을 수 없습니다",
    "path": "/schedules/20"
}
 ```

</div>
</details>

<details>
<summary>일정 생성</summary>
<div markdown="1">

| **이름**   | **타입**               | **설명** | **필수여부** |
|----------|----------------------|--------| --- |
| id       | INT (AUTO_INCREMENT) | 일정 id  | Y |
| title    | VARCHAR(200)         | 일정 제목  | Y |
| contents | VARCHAR(200)         | 일정 내용  | Y |
| username | VARCHAR(40)          | 유저 이름  | Y |

### Request

```
POST 'localhost:8080/schedules/{id}'
```

```json
{
  "title": "스파르타",
  "contents": "스프링"
}
```

### Response

Success HTTP Status : 201

Fail HTTP Status : 400, 404

성공

``` json
{
    "id": 7,
    "title": "스파르타",
    "contents": "",
    "username": "Park"
}
 ```

실패 (등록된 유저가 없을 시)

``` json
{
    "timestamp": "2024-11-15T03:48:30.818+00:00",
    "status": 404,
    "error": "Not Found",
    "message": "해당 유저를 찾을 수 없습니다.",
    "path": "/schedules/20"
}
 ```

실패 (validation)

``` json
{
    "timestamp": "2024-11-15T03:48:51.455+00:00",
    "status": 400,
    "error": "Bad Request",
    "message": "Validation failed for object='scheduleRequestDto'. Error count: 1",
    "path": "/schedules/1"
}
 ```

</div>
</details>

<details>
<summary>일정 수정</summary>
<div markdown="1">

| **이름**   | **타입**               | **설명** | **필수여부** |
|----------|----------------------|--------| --- |
| id       | INT (AUTO_INCREMENT) | 일정 id  | Y |
| title    | VARCHAR(200)         | 일정 제목  | Y |
| contents | VARCHAR(200)         | 일정 내용  | Y |
| username | VARCHAR(40)          | 유저 이름  | Y |

### Request

```
PUT 'localhost:8080/schedules/{id}'
```

```json
{
  "title": "수정하기",
  "contents": "허니 아이스아메리카노로 바꿔주세요"
}
```

### Response

Success HTTP Status : 200

Fail HTTP Status : 404

성공

``` json
{
    "id": 1,
    "title": "수정하기",
    "contents": "허니 아이스아메리카노로 바꿔주세요",
    "username": "Kim"
}
 ```

실패

``` json
{
    "timestamp": "2024-11-15T03:52:16.391+00:00",
    "status": 404,
    "error": "Not Found",
    "message": "해당 일정을 찾을 수 없습니다",
    "path": "/schedules/22"
}
 ```

</div>
</details>

<details>
<summary>일정 삭제</summary>
<div markdown="1">

### Request

```
DELETE 'localhost:8080/schedules/{id}'
```

### Response

Success HTTP Status : 200

Fail HTTP Status : 404

성공

```

```

실패

``` json
{
    "timestamp": "2024-11-15T03:55:45.057+00:00",
    "status": 404,
    "error": "Not Found",
    "message": "해당 일정을 찾을 수 없습니다",
    "path": "/schedules/222"
}
 ```

</div>
</details>

## 댓글 API

<details>
<summary>댓글 생성</summary>
<div markdown="1">


| **이름**     | **타입**               | **설명** | **필수여부** |
|------------|----------------------|--------| --- |
| id         | INT (AUTO_INCREMENT) | 댓글 id  | Y |
| scheduleId | INT (AUTO_INCREMENT) | 일정 id  | Y |
| name       | VARCHAR(10)          | 유저 이름  | Y |
| contents   | VARCHAR(255)         | 댓글 내용  | Y |

### Request

```
POST 'localhost:8080/schedules/{scheduleId}/comments'
```

```json
{
  "contents": "Park"
}
```

### Response

Success HTTP Status : 201

Fail HTTP Status : 404

성공

``` json
{
    "id": 2,
    "name": "Park",
    "contents": "Park"
}
```

실패

``` json
{
    "timestamp": "2024-11-15T04:04:18.036+00:00",
    "status": 404,
    "error": "Not Found",
    "message": "해당 일정을 찾을 수 없습니다",
    "path": "/schedules/222/comments"
}
```

</div>
</details>

<details>
<summary>댓글 조회</summary>
<div markdown="1">


| **이름**     | **타입**               | **설명** | **필수여부** |
|------------|----------------------|--------| --- |
| id         | INT (AUTO_INCREMENT) | 댓글 id  | Y |
| scheduleId | INT (AUTO_INCREMENT) | 일정 id  | Y |
| name       | VARCHAR(10)          | 유저 이름  | Y |
| contents   | VARCHAR(255)         | 댓글 내용  | Y |

### Request

```
GET 'localhost:8080/schedules/{scheduleId}/comments'
```

### Response

Success HTTP Status : 200

``` json
[
    {
        "id": 2,
        "name": "Park",
        "contents": "Park"
    }
]
```
댓글이 없는 경우 빈 배열 반환

``` json
[]
```

</div>
</details>

<details>
<summary>댓글 수정</summary>
<div markdown="1">


| **이름**     | **타입**               | **설명** | **필수여부** |
|------------|----------------------|--------| --- |
| id         | INT (AUTO_INCREMENT) | 댓글 id  | Y |
| name       | VARCHAR(10)          | 유저 이름  | Y |
| contents   | VARCHAR(255)         | 댓글 내용  | Y |

### Request

```
PUT 'localhost:8080/schedules/comments/{id}'
```

```json
{
  "contents": "Park 의 댓글 수정하기"
}
```

### Response

Success HTTP Status : 200

Fail HTTP Status : 404

성공

``` json
{
    "id": 1,
    "name": "Park",
    "contents": "Park 의 댓글 수정하기"
}
```

실패

``` json
{
    "timestamp": "2024-11-15T04:11:08.548+00:00",
    "status": 404,
    "error": "Not Found",
    "message": "해당 댓글을 찾을 수 없습니다.",
    "path": "/schedules/comments/14"
}
```

</div>
</details>

<details>
<summary>댓글 삭제</summary>
<div markdown="1">


| **이름**     | **타입**               | **설명** | **필수여부** |
|------------|----------------------|--------| --- |
| id         | INT (AUTO_INCREMENT) | 댓글 id  | Y |


### Request

```
DELETE 'localhost:8080/schedules/comments/{id}'
```

### Response

Success HTTP Status : 200

Fail HTTP Status : 404

성공

```

```

실패

``` json
{
    "timestamp": "2024-11-15T04:12:49.569+00:00",
    "status": 404,
    "error": "Not Found",
    "message": "해당 댓글을 찾을 수 없습니다.",
    "path": "/schedules/comments/255"
}
```

</div>
</details>


## ERD
![](https://velog.velcdn.com/images/jelog_131/post/a472d4e6-a548-4cd0-9dd5-bdb85719dd4a/image.png)


---

## 트러블 슈팅
- 일정 수정하기
- 패스워드 암호화 이슈

    https://buly.kr/BeJCUtY