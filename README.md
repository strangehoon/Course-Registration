# 수강신청 토이 프로젝트
<img src = "https://user-images.githubusercontent.com/117654450/208507651-fbd2f034-894b-419c-ad81-44d98839af0f.png" height = "450px" width = "600px" allign = "left">

</br>


## 목차
- 📢 프로젝트 소개
- 📈 ERD
- 📢 기능 소개
  * 관리자
    + 1 공지사항 관리
      - 1.1 게시물 등록
      - 1.2 게시물 수정 및 삭제
    + 2 개설강좌 관리
      - 2.1 강좌 등록
      - 2.2 강좌 수정
    + 3 수강신청 내역 관리(구현중)
  * 학생
    + 1 공지사항 열람
    + 2 개설 강좌 검색
    + 3 담아두기 내역 조회
    + 4 수강신청 내역 조회(구현중)
- ✍️ 트러블 슈팅
- 🎁 배포


## 📢 프로젝트 소개
지금까지 공부해온 내용들을 토대로 심플한 수강신청 서비스를 만들어봤습니다. 

</br>

## 📈 ERD 
업데이트 중..

</br>

## 📢 기능 소개

홈 화면에서 로그인 버튼 외에는 동작하지 않습니다.
수강신청 사이트이기 때문에 따로 회원가입 기능은 구현하지 않았습니다.

로그인하면 신원에 따라 관리자 뷰와 학생 뷰로 나뉩니다. 


관리자 ID = manager,  PW = 0000 </br>
학생 ID = student,  PW = 1234


</br>


### 관리자 
관리자는 공지사항 관리, 개설강좌 관리, 수강신청 내역 관리 권한이 있습니다. 

<img src = "https://user-images.githubusercontent.com/117654450/208514848-d7edcab7-c13e-479a-8e93-6c25c61135aa.png" height = "450px" width = "600px" allign = "left">

#### 1. 공지사항 관리
공지사항들을 관리합니다. 공지사항을 등록, 수정, 삭제할 수 있습니다. </br>

게시물을 클릭하면 조회가 가능하며 수정 및 삭제할 수 있습니다.

<img src = "https://user-images.githubusercontent.com/117654450/209309562-0b477cb9-a563-434a-be07-5bc9542a3d57.png" height = "450px" width = "750px" allign = "left">

</br>

##### 1.1 게시물  등록

<img src = "https://user-images.githubusercontent.com/117654450/209310065-1c179ced-75bc-42c5-8e3c-175797c6b7f7.png" height = "350px" width = "300px" allign = "left">

<br>

##### 1.2 게시물  수정 및 삭제

<img src = "https://user-images.githubusercontent.com/117654450/209309790-aacc8ee4-bbf1-4346-8b82-d2567e6dd7db.png" height = "350px" width = "300px" allign = "left">


</br>

#### 2. 개설강좌 관리
이번 학기 개설되는 강좌들을 관리합니다. 강좌 정보를 등록, 수정, 삭제할 수 있습니다. 
<img src = "https://user-images.githubusercontent.com/117654450/208511310-b910bff1-ea35-4cab-93fd-c81add0504aa.png" height = "450px" width = "750px" allign = "left">

</br>

##### 2.1 강좌 등록

<img src = "https://user-images.githubusercontent.com/117654450/208681144-8ff0ce60-9c8e-4655-a28c-c809a442a7f8.png" height = "450px" width = "350px" allign = "left">

</br>

##### 2.2 강좌 수정

<img src = "https://user-images.githubusercontent.com/117654450/208680894-f09d3bd7-21ef-4cea-85b4-980b12ad41e4.png" height = "450px" width = "350px" allign = "left">

#### 3. 수강신청 내역 관리(구현중)

</br>
</br>

### 학생

학생은 공지사항 열람, 개설강좌 검색, 담아두기 내역 조회, 수강신청 내역 조회 권한이 있습니다.


#### 1. 공지사항 열람

<img src = "https://user-images.githubusercontent.com/117654450/209312585-2aacb3bb-4964-463c-93be-97c6919d140e.png" height = "450px" width = "750px" allign = "left">

</br>

학생은 공지사항을 열람만 할 수 있습니다.

<img src = "https://user-images.githubusercontent.com/117654450/209311429-4cf28c1d-b8b7-4804-89e5-e2e73e0ecd4d.png" height = "350px" width = "300px" allign = "left">


</br>

#### 2. 개설 강좌 검색
전공, 학년, 과목명에 따라 검색이 가능합니다. '담아두기' 버튼을 누르면 장바구니에 담을 수 있습니다. 강좌 시간을 중복해서 택할 수 없습니다. </br>

예를 들어 월5화5수5인 수업과 월5목5금5인 수업은 동시에 담을 수 없습니다. 

<img src = "https://user-images.githubusercontent.com/117654450/208515388-e096d0cf-cd52-4e04-a122-681563cda8d2.png" height = "450px" width = "750px" allign = "left">

</br>

#### 3. 담아두기 내역 조회

<img src = "https://user-images.githubusercontent.com/117654450/208828345-ceb54486-e397-49c1-bf3b-8eacfda952bd.png" height = "200px" width = "550px" allign = "left">


</br>

#### 4. 수강신청 내역 조회(구현중)

</br>
</br>

## ✍️ 트러블 슈팅
[글 번호 정렬](https://velog.io/@strangehoon/%EB%AC%B8%EC%A0%9C%ED%95%B4%EA%B2%B0-%ED%8E%98%EC%9D%B4%EC%A7%95%EC%B2%98%EB%A6%AC)

</br>

## 🎁 배포
업데이트 중..
