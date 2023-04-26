# 🖐 홍익신청
<img src = "https://user-images.githubusercontent.com/117654450/208507651-fbd2f034-894b-419c-ad81-44d98839af0f.png" height = "450px" width = "600px" allign = "left">

</br>

## 📝 개요

<details>
<summary>접기/펼치기</summary> </br>

* **프로젝트명** : 홍익신청

* **주제** : 기존의 홍익대학교의 수강신청 사이트를 소량화하여 클론 프로젝트를 진행했습니다.

* **기간** : 2022년 11월 16일 ~ 2023년 1월 16일

* **팀원** : [이상훈](https://github.com/strangehoon) 


</details>

</br>


## 📈 ERD 

<details>
<summary>접기/펼치기</summary> </br>

<img src = "https://user-images.githubusercontent.com/117654450/234635670-496a94aa-2966-45f3-9376-baa9c36cc2dc.png" height = "450px" width = "700px" allign = "left">

</details>

</br>

## 💡 주요 기능

<details>
<summary>접기/펼치기</summary> </br>


**❗️❗ 권한(관리자, 학생)에 따라 이용할 수 있는 서비스가 다릅니다.❗️❗️**

> 📌 홈 화면에서 로그인 버튼 외에는 동작하지 않습니다. </br>
수강신청 사이트이기 때문에 따로 회원가입 기능은 구현하지 않았습니다. </br>
로그인하면 신원에 따라 관리자 뷰와 학생 뷰로 나뉩니다. </br>
관리자 ID = manager,  PW = 0000 </br>
학생 ID = student,  PW = 1234

</br>

### 관리자 
관리자는 **공지사항 관리, 개설강좌 관리, 수강신청 내역 관리** 권한이 있습니다. 
</br>

* **1. 공지사항 관리**

    공지사항들을 관리합니다. </br>
    공지사항을 등록, 수정, 조회, 삭제할 수 있습니다. </br>

    <img src = "https://user-images.githubusercontent.com/117654450/209309562-0b477cb9-a563-434a-be07-5bc9542a3d57.png" height = "450px" width = "750px" allign = "left">

* **1.1 게시물  등록**

    <img src = "https://user-images.githubusercontent.com/117654450/209310065-1c179ced-75bc-42c5-8e3c-175797c6b7f7.png" height = "350px" width = "300px" allign = "left">

* **1.2 게시물  수정 및 삭제**

    <img src = "https://user-images.githubusercontent.com/117654450/209309790-aacc8ee4-bbf1-4346-8b82-d2567e6dd7db.png" height = "350px" width = "300px" allign = "left">

</br>

* **2. 개설강좌 관리**
    
    이번 학기 개설되는 강좌들을 관리합니다. </br>
    강좌 정보를 등록, 수정, 삭제할 수 있습니다. 
    <img src = "https://user-images.githubusercontent.com/117654450/208511310-b910bff1-ea35-4cab-93fd-c81add0504aa.png" height = "450px" width = "750px" allign = "left">

* **2.1 강좌 등록**

    <img src = "https://user-images.githubusercontent.com/117654450/208681144-8ff0ce60-9c8e-4655-a28c-c809a442a7f8.png" height = "350px" width = "300px" allign = "left">

* **2.2 강좌 수정**

    <img src = "https://user-images.githubusercontent.com/117654450/208680894-f09d3bd7-21ef-4cea-85b4-980b12ad41e4.png" height = "350px" width = "300px" allign = "left">

</br>

* **3. 수강신청 내역 관리**

</br>

### 학생

학생은 **공지사항 열람, 개설강좌 검색, 담아두기 내역 조회, 수강신청 내역 조회 권한**이 있습니다.

* **1. 공지사항 열람**

<img src = "https://user-images.githubusercontent.com/117654450/209312585-2aacb3bb-4964-463c-93be-97c6919d140e.png" height = "450px" width = "750px" allign = "left">

</br>

<img src = "https://user-images.githubusercontent.com/117654450/209311429-4cf28c1d-b8b7-4804-89e5-e2e73e0ecd4d.png" height = "350px" width = "300px" allign = "left">

> 학생은 공지사항을 열람만 할 수 있습니다.

</br>

* **2. 개설 강좌 검색**

전공, 학년, 과목명에 따라 검색이 가능합니다. '담아두기' 버튼을 누르면 장바구니에 담을 수 있습니다. 

담아두기에는 다음과 같은 제약사항이 있습니다.

> 동일한 강좌는 담을 수 없습니다. </br>
19학점을 초과해서 담을 수 없습니다. </br>
강좌 시간을 중복해서 택할 수 없습니다. </br>

</br>

<img src = "https://user-images.githubusercontent.com/117654450/208515388-e096d0cf-cd52-4e04-a122-681563cda8d2.png" height = "450px" width = "750px" allign = "left">

</br>

* **3. 담아두기 내역 조회**

'담은 과목 수강신청하기' 버튼을 누르면 수강신청이 됩니다.

<img src = "https://user-images.githubusercontent.com/117654450/209866782-cef456d3-d6a5-4bff-b7bd-8944ce55d18e.png" height = "450px" width = "750px" allign = "left">

</br>

* **4. 수강신청 내역 조회**

이번 학기 본인이 시간표를 열람할 수 있습니다.

</br>

<img src = "https://user-images.githubusercontent.com/117654450/209866915-bf1afe57-9463-4101-8615-6405a83edb08.png" height = "550px" width = "450px" allign = "left">

</details>

</br>

## ⚒ 기술스택
<details>
<summary>접기/펼치기</summary> </br> 

**Frontend** 

<img src="https://img.shields.io/badge/HTML-E34F26?style=flat&logo=HTML&logoColor=white"/>
<img src="https://img.shields.io/badge/CSS-1572B6?style=flat&logo=CSS&logoColor=white"/>
<img src="https://img.shields.io/badge/JavaScript-E34F26?style=flat&logo=JavaScript&logoColor=white"/>
<img src="https://img.shields.io/badge/Bootstrap-7952B3?style=flat&logo=Bootstrap&logoColor=white"/>
<img src="https://img.shields.io/badge/Jquery-0769AD?style=flat&logo=Jquery&logoColor=white"/>

</br>

**Backend**

<img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat&logo=SpringBoot&logoColor=white"/> <img src="https://img.shields.io/badge/Spring Data Jpa-E31E52?style=flat&logo=Spring Data Jpa&logoColor=white"/>
<img src="https://img.shields.io/badge/Querydsl-0096D6?style=flat&logo=Querydsl&logoColor=white"/> 
<img src="https://img.shields.io/badge/MySql-4479A1?style=flat&logo=MySql&logoColor=white"/>
<img src="https://img.shields.io/badge/Gradle-02303A?style=flat&logo=Gradle&logoColor=white"/>
<img src="https://img.shields.io/badge/GitHub-181717?style=flat&logo=GitHub&logoColor=white"/>
<img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=flat&logo=Thymeleaf&logoColor=white"/>

</details>

</br>

## ⚖️ 기술적 의사결정
<details>
<summary>접기/펼치기</summary> </br>

</br>

</details>

</br>

## 🔨 트러블슈팅
<details>
<summary>접기/펼치기</summary> </br>

[글 번호 정렬](https://velog.io/@strangehoon/%EB%AC%B8%EC%A0%9C%ED%95%B4%EA%B2%B0-%ED%8E%98%EC%9D%B4%EC%A7%95%EC%B2%98%EB%A6%AC) </br>
[비동기, 동기](https://velog.io/@strangehoon/%ED%8A%B8%EB%9F%AC%EB%B8%94-%EC%8A%88%ED%8C%85-ajax-%EB%B9%84%EB%8F%99%EA%B8%B0-%EB%8F%99%EA%B8%B0)

</details>


