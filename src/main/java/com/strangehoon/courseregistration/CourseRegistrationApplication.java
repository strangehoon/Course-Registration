package com.strangehoon.courseregistration;

import com.strangehoon.courseregistration.domain.Student;
import com.strangehoon.courseregistration.dto.BoardDto;
import com.strangehoon.courseregistration.dto.PartClassDto;
import com.strangehoon.courseregistration.dto.StudentDto;
import com.strangehoon.courseregistration.repository.MajorRepository;
import com.strangehoon.courseregistration.repository.StudentRepository;
import com.strangehoon.courseregistration.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
@EnableJpaAuditing
public class CourseRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseRegistrationApplication.class, args);
	}

	private final PartClassService partClassService;
	private final MajorService majorService;
	private final StudentService studentService;
	private final ManagerService managerService;
	private final BoardService boardService;


	/*테스트용 데이터*/
	@PostConstruct
	public void init() {
		//전공
		majorService.createMajor("전자전기공학부");
		majorService.createMajor("컴퓨터공학부");
		majorService.createMajor("신소재공학부");
		majorService.createMajor("경영학부");
		majorService.createMajor("영어영문학과");

		//회원(학생)
		studentService.createStudent(new StudentDto("student1", "1234", "전자전기공학부", 4,"이상훈","010-1234-5678", 4.1 ));

		//회원(관리자)
		managerService.createManager("manager", "0000");

		//분반(전자전기공학)
		partClassService.createPartClass(new PartClassDto("106827-1", "창직 IOT 종합설계입문", 1, 3, 18, 18, "신형식", "화2화3화4", "P211", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106827-2", "창직 IOT 종합설계입문", 1, 3, 18, 18, "신형식", "화6화7화8", "P211", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106827-3", "창직 IOT 종합설계입문", 1, 3, 18, 18, "노승문", "수1수2수3", "P211", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106827-4", "창직 IOT 종합설계입문", 1, 3, 18, 18, "노승문", "월1월2월3", "P111", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106827-5", "창직 IOT 종합설계입문", 1, 3, 18, 18, "최창식", "목2목3목4", "P211", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("013706-1", "전기회로실험및설계", 2, 3, 18, 18, "정하봉", "수6수7", "P205", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("013706-2", "전기회로실험및설계", 2, 3, 18, 18, "박동욱", "월7월8", "P204", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("013706-3", "전기회로실험및설계", 2, 3, 18, 18, "계영철", "금1금2", "P203", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("013706-4", "전기회로실험및설계", 2, 3, 18, 18, "양현석", "월1월2", "P101", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("013706-5", "전기회로실험및설계", 2, 3, 18, 18, "박상주", "목7목8", "P204", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106302-1", "물리전자", 2, 3, 50, 50, "성혁기", "월1화1수1", "P301", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106302-2", "물리전자", 2, 3, 50, 50, "성혁기", "월3화3수3", "P301", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106302-3", "물리전자", 2, 3, 50, 50, "성혁기", "월6화6수6", "P301", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106302-4", "물리전자", 2, 3, 50, 50, "김영민", "화7목7금7", "P301", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106302-5", "물리전자", 2, 3, 50, 50, "김영민", "화8목8금8", "P301", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106404-1", "신호와시스템", 3, 3, 50, 50, "허서원", "월5화5수5", "P302", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106404-2", "신호와시스템", 3, 3, 50, 50, "허서원", "월6화6수6", "P302", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106404-3", "신호와시스템", 3, 3, 50, 50, "계영철", "화2화3목7", "P303", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106404-4", "신호와시스템", 3, 3, 50, 50, "계영철", "월1월2금1", "P303", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106404-5", "신호와시스템", 3, 3, 50, 50, "계영철", "월5월6목5", "P303", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106611-1", "반도체공학(2)", 3, 3, 50, 50, "김형탁", "화5화6금1", "P304", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106611-2", "반도체공학(2)", 3, 3, 50, 50, "김형탁", "월8수8목8", "P304", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106611-3", "반도체공학(2)", 3, 3, 50, 50, "박상연", "월3월4수1", "P305", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106611-4", "반도체공학(2)", 3, 3, 50, 50, "박상연", "월7월8수8", "P305", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106611-5", "반도체공학(2)", 3, 3, 50, 50, "박상연", "화7목7금7", "P305", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106612-1", "집적회로설계", 3, 3, 50, 50, "김종선", "월1화1수1", "P401", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106612-2", "집적회로설계", 3, 3, 50, 50, "김종선", "월2화2수2", "P401", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106612-3", "집적회로설계", 3, 3, 50, 50, "김종선", "월5화5수5", "P401", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106612-4", "집적회로설계", 3, 3, 50, 50, "김영민", "수6수7목5", "P402", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106612-5", "집적회로설계", 3, 3, 50, 50, "김영민", "월1금1금2", "P402", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106825-1", "IT종합설계프로젝트", 4, 3, 18, 18, "강동우", "월1월2월3", "P101", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106825-2", "IT종합설계프로젝트", 4, 3, 18, 18, "신훈영", "화1화2화3", "P101", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106825-3", "IT종합설계프로젝트", 4, 3, 18, 18, "차호영", "금7금8금9", "P102", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106825-4", "IT종합설계프로젝트", 4, 3, 18, 18, "이기성", "월1화1수1", "P102", "전자전기공학부"));
		partClassService.createPartClass(new PartClassDto("106825-5", "IT종합설계프로젝트", 4, 3, 18, 18, "신형식", "월6목6금6", "P103", "전자전기공학부"));

		//분반(컴퓨터공학)
		partClassService.createPartClass(new PartClassDto("013312-1", "자료구조및프로그래밍", 2, 4, 40, 40, "송하윤", "월2월3목2목3", "T501", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("013312-2", "자료구조및프로그래밍", 2, 4, 40, 40, "송하윤", "월6월7목6목7", "T501", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("013312-3", "자료구조및프로그래밍", 2, 4, 40, 40, "배성일", "화2화3금2금3", "T502", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("013312-4", "자료구조및프로그래밍", 2, 4, 40, 40, "배성일", "월7월8수1수2", "T502", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("013312-5", "자료구조및프로그래밍", 2, 4, 40, 40, "배성일", "월5월6목1목2", "T502", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101410-1", "데이터통신", 2, 3, 50, 50, "박준상", "화7화8화8", "T501", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101410-2", "데이터통신", 2, 3, 50, 50, "박준상", "월2화2수2", "T501", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101410-3", "데이터통신", 2, 3, 50, 50, "박준상", "월3화3수3", "T501", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101410-4", "데이터통신", 2, 3, 50, 50, "김상곤", "월8화8목8", "T505", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101410-5", "데이터통신", 2, 3, 50, 50, "김상곤", "월9화9목9", "T505", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101408-1", "어셈블리언어및실습", 2, 3, 50, 50, "표창우", "월1화1목1", "T501", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101408-2", "어셈블리언어및실습", 2, 3, 50, 50, "표창우", "월2화2목2", "T501", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101408-3", "어셈블리언어및실습", 2, 3, 50, 50, "표창우", "월3화3목3", "T501", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101408-4", "어셈블리언어및실습", 2, 3, 50, 50, "박재영", "수1목1금1", "T502", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101408-5", "어셈블리언어및실습", 2, 3, 50, 50, "박재영", "수2목2금2", "T502", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101511-1", "운영체제", 3, 3, 40, 40, "김선일", "월2목3목4", "T503", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101511-2", "운영체제", 3, 3, 40, 40, "김선일", "월1목1목2", "T503", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101511-3", "운영체제", 3, 3, 40, 40, "김선일", "월6월7목7", "T503", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101511-4", "운영체제", 3, 3, 40, 40, "이장호", "수1목1금1", "T502", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101511-5", "운영체제", 3, 3, 40, 40, "이장호", "월2월3수3", "T502", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101613-1", "기초데이터베이스", 3, 4, 50, 50, "김은삼", "월1월2수1수2", "T504", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101613-2", "기초데이터베이스", 3, 4, 50, 50, "김은삼", "월7월8목7목8", "T504", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101613-3", "기초데이터베이스", 3, 4, 50, 50, "윤영", "화1화2금1금2", "T501", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101613-4", "기초데이터베이스", 3, 4, 50, 50, "윤영", "화6화7목6목7", "T501", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101613-5", "기초데이터베이스", 3, 4, 50, 50, "윤영", "수3수4금3금4", "T501", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101606-1", "오토마타", 3, 4, 40, 40, "권건우", "수1수2금1", "T505", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101606-2", "오토마타", 3, 4, 40, 40, "권건우", "월6월7화1", "T505", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101606-3", "오토마타", 3, 4, 40, 40, "권건우", "월3월4화3", "T505", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101606-4", "오토마타", 3, 4, 40, 40, "김상곤", "수3목3금3", "T506", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101606-5", "오토마타", 3, 4, 40, 40, "김상곤", "수1목1금1", "T506", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101812-1", "창직종합설계프로젝트(2)", 4, 3, 12, 12, "하란", "월1월2월3", "T301", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101812-2", "창직종합설계프로젝트(2)", 4, 3, 12, 12, "김선일", "월1월2월3", "T302", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101812-3", "창직종합설계프로젝트(2)", 4, 3, 12, 12, "배성일", "화1화2화3", "T303", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101812-4", "창직종합설계프로젝트(2)", 4, 3, 12, 12, "박준철", "수1수2수3", "T304", "컴퓨터공학부"));
		partClassService.createPartClass(new PartClassDto("101812-5", "창직종합설계프로젝트(2)", 4, 3, 12, 12, "이장호", "목1목2목3", "T305", "컴퓨터공학부"));

		//분반(신소재공학부)
		partClassService.createPartClass(new PartClassDto("124201-1", "재료물성개론", 1, 3, 50, 50, "이기영", "월45", "K611", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("124201-2", "재료물성개론", 1, 3, 50, 50, "이기영", "화45", "K612", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("124201-3", "재료물성개론", 1, 3, 50, 50, "이동욱", "수45", "K613", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("124201-4", "재료물성개론", 1, 3, 50, 50, "이동욱", "목45", "K614", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("124201-5", "재료물성개론", 1, 3, 50, 50, "이동욱", "금45", "K615", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125401-1", "재료열역학", 2, 3, 50, 50, "황진하", "월1화1수1", "K311", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125401-2", "재료열역학", 2, 3, 50, 50, "황진하", "월2화2수2", "K312", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125401-3", "재료열역학", 2, 3, 50, 50, "황진하", "월3화3수3", "K313", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125401-4", "재료열역학", 2, 3, 50, 50, "이원규", "화6목6금6", "K314", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125401-5", "재료열역학", 2, 3, 50, 50, "이원규", "화7목7금7", "K315", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125402-1", "재료탄소성학", 2, 3, 50, 50, "이원규", "화1목1금1", "K211", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125402-2", "재료탄소성학", 2, 3, 50, 50, "이원규", "화2목2금2", "K212", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125402-3", "재료탄소성학", 2, 3, 50, 50, "엄태식", "월1월2금7", "K213", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125402-4", "재료탄소성학", 2, 3, 50, 50, "엄태식", "화1화2금8", "K214", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125402-5", "재료탄소성학", 2, 3, 50, 50, "엄태식", "수1수2금1", "K215", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125614-1", "디스플레이재료", 3, 3, 50, 50, "양희선", "월1월2화7", "K411", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125614-2", "디스플레이재료", 3, 3, 50, 50, "양희선", "월3월4화3", "K411", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125614-3", "디스플레이재료", 3, 3, 50, 50, "양희선", "월6화6목6", "K411", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125611-1", "기능재료", 3, 3, 40, 40, "박병남", "월3화3목3", "K111", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125611-2", "기능재료", 3, 3, 40, 40, "박병남", "월8화8목8", "K111", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125621-1", "반도체재료및측정실험", 3, 2, 15, 15, "김우진", "월3월4", "K711", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125621-2", "반도체재료및측정실험", 3, 2, 15, 15, "이경남", "화3화4", "K711", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125621-3", "반도체재료및측정실험", 3, 2, 15, 15, "박병진", "화5화6", "K711", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125621-4", "반도체재료및측정실험", 3, 2, 15, 15, "양희선", "금3금4", "K711", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125621-5", "반도체재료및측정실험", 3, 2, 15, 15, "양희선", "금6금7", "K711", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125718-1", "창의적종합설계", 4, 3, 15, 15, "이재호", "월7월8월9", "K411", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125718-2", "창의적종합설계", 4, 3, 15, 15, "박병남", "화7화8화9", "K411", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125718-3", "창의적종합설계", 4, 3, 15, 15, "신소우", "수7수8수9", "K411", "신소재공학부"));
		partClassService.createPartClass(new PartClassDto("125718-4", "창의적종합설계", 4, 3, 15, 15, "김경남", "목7목8목9", "K411", "신소재공학부"));

		//분반(경영학부)
		partClassService.createPartClass(new PartClassDto("033102-1", "경제학원론", 1, 3, 45, 45, "이대창", "월1화1수1", "B301", "경영학부"));
		partClassService.createPartClass(new PartClassDto("033102-2", "경제학원론", 1, 3, 45, 45, "이대창", "월2화2수2", "B302", "경영학부"));
		partClassService.createPartClass(new PartClassDto("033102-3", "경제학원론", 1, 3, 45, 45, "이종건", "월6화6수6", "B303", "경영학부"));
		partClassService.createPartClass(new PartClassDto("033102-4", "경제학원론", 1, 3, 45, 45, "이종건", "화1수1목1", "B304", "경영학부"));
		partClassService.createPartClass(new PartClassDto("033102-5", "경제학원론", 1, 3, 45, 45, "이종건", "화7수7목7", "B305", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311204-1", "경영통계학", 1, 3, 55, 55, "박민재", "화1수1목1", "B401", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311204-2", "경영통계학", 1, 3, 55, 55, "박민재", "화5수5목5", "B402", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311204-3", "경영통계학", 1, 3, 55, 55, "이미림", "목7금7금8", "B403", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311204-4", "경영통계학", 1, 3, 55, 55, "이미림", "화1화2수3", "B404", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311204-5", "경영통계학", 1, 3, 55, 55, "이미림", "월5화5수5", "B405", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311406-1", "생산과경영", 2, 3, 55, 55, "류준호", "월5월6월7", "B101", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311406-2", "생산과경영", 2, 3, 55, 55, "이진표", "화5화6화7", "B101", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311406-3", "생산과경영", 2, 3, 55, 55, "이진표", "목5목6목7", "B101", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311406-4", "생산과경영", 2, 3, 55, 55, "박민호", "금1금2금3", "B101", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311406-5", "생산과경영", 2, 3, 55, 55, "박민호", "목1목2목3", "B101", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311407-1", "투자론", 3, 3, 50, 50, "강인선", "목1목2목3", "B201", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311407-2", "투자론", 3, 3, 50, 50, "박미선", "목7목8목9", "B202", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311407-3", "투자론", 3, 3, 50, 50, "박미선", "수1목1금1", "B203", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311407-4", "투자론", 3, 3, 50, 50, "이진미", "금1금2금3", "B204", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311610-1", "소비자행동", 3, 3, 50, 50, "이호배", "월1월2월3", "B701", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311610-2", "소비자행동", 3, 3, 50, 50, "이호배", "월5월6월7", "B702", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311610-3", "소비자행동", 3, 3, 50, 50, "김춘식", "화1화2화3", "B703", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311610-4", "소비자행동", 3, 3, 50, 50, "김춘식", "수1수2수3", "B704", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311610-5", "소비자행동", 3, 3, 50, 50, "김춘식", "수5수6수7", "B705", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311814-1", "기업리스크관리", 4, 3, 50, 50, "신병헌", "수5수6수7", "B801", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311814-2", "기업리스크관리", 4, 3, 50, 50, "신병헌", "월5월6월7", "B802", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311814-3", "기업리스크관리", 4, 3, 50, 50, "신병헌", "월5화5수5", "B803", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311814-4", "기업리스크관리", 4, 3, 50, 50, "전홍배", "월5화5목5", "B804", "경영학부"));
		partClassService.createPartClass(new PartClassDto("311814-5", "기업리스크관리", 4, 3, 50, 50, "전홍배", "금5금6금7", "B805", "경영학부"));

		//분반(영어영문학과)
		partClassService.createPartClass(new PartClassDto("201207-1", "영어학입문", 1, 3, 30, 30, "임태연", "월5월6월7", "C401", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("201207-2", "영어학입문", 1, 3, 30, 30, "임태연", "화5화6화7", "C402", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("201207-3", "영어학입문", 1, 3, 30, 30, "박현주", "수5수6수7", "C403", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("201207-4", "영어학입문", 1, 3, 30, 30, "박현주", "목5목6목7", "C404", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("201207-5", "영어학입문", 1, 3, 30, 30, "박현주", "금5금6금7", "C405", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("023404-1", "미국문학사", 2, 3, 40, 40, "조충환", "월1화1수1", "C301", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("023404-2", "미국문학사", 2, 3, 40, 40, "조충환", "월2화2수2", "C302", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("023404-3", "미국문학사", 2, 3, 40, 40, "이정래", "월3수3금3", "C303", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("023404-4", "미국문학사", 2, 3, 40, 40, "이정래", "월6수6금6", "C304", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("023404-5", "미국문학사", 2, 3, 40, 40, "이정래", "월7수7금7", "C405", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("201411-1", "영미시의이해", 2, 3, 30, 30, "김미순", "월7수7금7", "C405", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("201411-2", "영미시의이해", 2, 3, 30, 30, "김미순", "월8수8금8", "C405", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("201411-3", "영미시의이해", 2, 3, 30, 30, "김미순", "월9수9금9", "C405", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("201415-1", "영어담화와정보", 3, 3, 30, 30, "염재일", "월1화1수1", "C501", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("201415-2", "영어담화와정보", 3, 3, 30, 30, "염재일", "월1월2월3", "C502", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("201415-3", "영어담화와정보", 3, 3, 30, 30, "염재일", "월5수5금5", "C503", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("201415-4", "영어담화와정보", 3, 3, 30, 30, "김미선", "화2화3목7", "C504", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("201415-5", "영어담화와정보", 3, 3, 30, 30, "김미선", "월7수7금1", "C505", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("201703-1", "세익스피어", 3, 3, 50, 50, "박일형", "월1수1금1", "C605", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("201703-2", "세익스피어", 3, 3, 50, 50, "박일형", "월2수2금2", "C605", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("201703-3", "세익스피어", 3, 3, 50, 50, "박일형", "월7수7금7", "C605", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("201813-1", "영어문장구조", 3, 3, 40, 40, "이종우", "월7수7금7", "C602", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("201813-2", "영어문장구조", 3, 3, 40, 40, "이종우", "월8수8금8", "C602", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("201813-3", "영어문장구조", 3, 3, 40, 40, "이정일", "화1화2화3", "C602", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("201813-4", "영어문장구조", 3, 3, 40, 40, "이정일", "수1수2수3", "C602", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("201811-1", "포스트모던영미드라마", 4, 3, 40, 40, "김다산", "목1목2목3", "C301", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("201811-2", "포스트모던영미드라마", 4, 3, 40, 40, "김다산", "금1금2금3", "C302", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("201811-3", "포스트모던영미드라마", 4, 3, 40, 40, "이승미", "월1목1금1", "C303", "영어영문학과"));
		partClassService.createPartClass(new PartClassDto("201811-4", "포스트모던영미드라마", 4, 3, 40, 40, "이승미", "월6목6금6", "C304", "영어영문학과"));


		//게시물
		boardService.savePost(new BoardDto("관리자1", "[필독]계절학기 수강료 납부 관련 공지","오늘부터 12월 20일(화) 16:00까지 계절학기 수강료 납부 기간입니다. 계절학기를 신청한 모든 학생들은 반드시 기한 내 수업료를 납부하시기 바랍니다."));
		boardService.savePost(new BoardDto("관리자2", "[계절학기]폐강 수업 관련 공지","2022학년도 동계 계절학기 폐강과목을 안내하오니, 폐강된 과목을 수강신청한 학생은 12.21(수) 15:00 ~ 12.22(목) 17:00까지 온라인으로 수강정정하시기 바랍니다."));
		boardService.savePost(new BoardDto("관리자1", "[계절학기]2022학년도 동계 계절학기 폐강 및 환불 공지","폐강과목 공고 및 폐강과목에 대한 수강정정 서울캠퍼스의 2022학년도 동계 계절학기 폐강과목을 [붙임1]과 같이 안내하오니, 폐강된 과목을 수강신청한 학생은 12.21(수) 15:00 ~ 12.22(목) 17:00까지 온라인으로 수강정정하시기 바랍니다. "));
		boardService.savePost(new BoardDto("관리자2", "[사이버강좌] 2022학년도 계절학기 출석 및 수강방법 안내\n",
				"사이버강좌 출석 및 수강방법 안내를 다음과 같이 안내하오니 수강에 차질 없도록 참고 바랍니다."));

		boardService.savePost(new BoardDto("관리자2", "[일반] 2022학년도 동계 방학 사회봉사 신청 안내\n",
				"23-1학기부터 서울캠퍼스 사회봉사 교과목 수강신청 방법이 변경되었습니다. 반드시 신청 방법을 확인하여 주시기 바랍니다. 2022학년도 동계 학기 [사회봉사] 교과목을 다음과 같이 개설하오니 희망자는 신청하시기 바랍니다."));
		boardService.savePost(new BoardDto("관리자2", "[일반]2023학년도 1학기 재입학 추가 신청 안내"  ,"재입학 추가 신청은 세종캠퍼스에 한하여 진행됩니다.\n" +
				"1. 재입학 추가 신청기간 : 2022. 12 5(월) - 2022. 12. 16(금) 16:00까지(공휴일 및 주말 제외, 기한 엄수)."));
		boardService.savePost(new BoardDto("관리자3", "[일반]2023년 2월 조기졸업 신청서 제출 안내\n",
				"조기졸업 대상자 중 조기졸업을 희망하는 학생은 아래의 조기졸업 신청서(첨부서류 포함)를 작성하여 해당 주전공 학과로 제출하여 주시기 바랍니다. 제출 기간 및 방법에 대한 상세 내용은 첨부파일 참고하시기 바랍니다.  끝."));
		boardService.savePost(new BoardDto("관리자1", "[계절학기]일반선택 대학수학(2)강좌 수강제한인원 변경 안내\n",
				"12월 24일 금일 17시 이후 012202-503~505 대학수학(2) 강좌의 수강제한인원이 0/0/0/30/35에서 0/0/0/30/31로 변경 될 예정입니다. 남은 수강신청에 참고 부탁드립니다. 감사합니다."));
		boardService.savePost(new BoardDto("관리자3", "[일반]2023년 2월 졸업예정자 전공명 선택 안내\n",
				"학과 명칭변경, 전공폐지 등의 학과 개편이 이루어지기 전에 해당 학과에 재적 중이던 학생은 졸업 시 희망에 따라 전공명을 선택할 수 있습니다. 전공명 변경을 희망하는 금 학기 졸업예정자는 다음의 절차를 유의하시어 기한 내 신청하시기 바랍니다."));
		boardService.savePost(new BoardDto("관리자1", "[일반]2022학년도 동계 계절학기 수업 및 평가 운영방침 안내\n",
				"2022학년도 동계 계절학기 수업 및 평가 운영방침을 아래와 같이 안내합니다."));
		boardService.savePost(new BoardDto("관리자1", "[일반]2022학년도 2학기 강의평가 실시 안내\n",
				"2022학년도 2학기 강의평가를 아래와 같이 시행하오니, 강의의 질을 높이고 학생들의 강의만족도를 제고할 수 있도록 학생 여러분들의 적극적이고 정확한 참여 부탁드립니다."));
		boardService.savePost(new BoardDto("관리자2", "[일반]학생 클래스넷 내 수료 확인서, 수강신청확인서 출력 가능 안내\n",
				"학생 클래스넷에서 수료(예정)확인서서, 수강신청확인서가 출력 가능하니 참고하시어 필요 시 활용하시기 바랍니다."));























	}
}
