# InstagramService
[![Build Status](https://travis-ci.org/apearc03/InstagramScraperService.svg?branch=master)](https://travis-ci.org/apearc03/InstagramScraperService)

Small Spring Boot microservice that scrapes Instagram user pages and returns the data in a JSON format.
Multiple users can be scraped in a single request.

User pages are scraped on different threads for faster response times.

# Requests
GET with usernames as request parameters.

curl -X GET "https://gramscraper.herokuapp.com/usernames?usernames=davidbeckham,KimKardashian" -H "accept: */*"

POST with usernames in a JSON array of the request body.

curl -X POST "https://gramscraper.herokuapp.com/usernames" -H "accept: */*" -H "Content-Type: application/json" -d "[\"davidbeckham\", \"KimKardashian\"]"

# Response
Response for both of the above requests.
```json
[
  {
    "username": "davidbeckham",
    "userinfo": {
      "full_name": "David Beckham",
      "biography": "",
      "followersCount": 56490859,
      "followingCount": 383,
      "postsCount": 1021,
      "is_private": false,
      "is_verified": true,
      "profile_pic_url": "https://scontent-lht6-1.cdninstagram.com/vp/5a7cffff653c84a8791e4d7fb4378fb9/5DC61FA3/t51.2885-19/s150x150/11848873_416913721845060_1906915195_a.jpg?_nc_ht=scontent-lht6-1.cdninstagram.com",
      "profile_pic_url_hd": "https://scontent-lht6-1.cdninstagram.com/vp/5a7cffff653c84a8791e4d7fb4378fb9/5DC61FA3/t51.2885-19/s150x150/11848873_416913721845060_1906915195_a.jpg?_nc_ht=scontent-lht6-1.cdninstagram.com",
      "is_business_account": false,
      "is_joined_recently": false,
      "highlight_reel_count": 10
    }
  },
  {
    "username": "KimKardashian",
    "userinfo": {
      "full_name": "Kim Kardashian West",
      "biography": "",
      "followersCount": 142230465,
      "followingCount": 116,
      "postsCount": 4870,
      "is_private": false,
      "is_verified": true,
      "profile_pic_url": "https://scontent-lht6-1.cdninstagram.com/vp/c3c05245a4a4fb5c67ddef137a3aa963/5D888C2C/t51.2885-19/s150x150/41326196_329788961105496_8866535953355767808_n.jpg?_nc_ht=scontent-lht6-1.cdninstagram.com",
      "profile_pic_url_hd": "https://scontent-lht6-1.cdninstagram.com/vp/bea2a4b57c7229244337a8c54dc3e0c3/5DAC23DC/t51.2885-19/s320x320/41326196_329788961105496_8866535953355767808_n.jpg?_nc_ht=scontent-lht6-1.cdninstagram.com",
      "is_business_account": false,
      "is_joined_recently": false,
      "highlight_reel_count": 15
    }
  }
]
```
# Running Locally

```console
git clone https://github.com/apearc03/InstagramScraperService.git
```
```console
mvn clean install
```
```console
mvn spring-boot:run
```

Default port is 8080


