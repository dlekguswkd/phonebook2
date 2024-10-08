package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhonebookDao;
import com.javaex.vo.PersonVo;


@WebServlet("/pbc")
public class PhonebookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	// Controller 접수받는일 (업무구분)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//action이 뭔지 알아야함
		String action = request.getParameter("action");
		System.out.println(action);
		
		if ("list".equals(action)) { 	// action.equals("list")인것을 null을 피하기위해 반대로 씀(주소에 ?~안쓰는 상황)

			//접수
			System.out.println("리스트요청");
			
			//db데이터 가져오기
			PhonebookDao phonebookDao = new PhonebookDao();
			List<PersonVo> personList = phonebookDao.getPersonList();
			// System.out.println(personList);
			
			//화면그리기 --> 포워드
			//request 에 리스트 주소 넣기
			request.setAttribute("personList", personList);
			
			//포워드 (webapp에서 시작)
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp");
			rd.forward(request, response);
			
			
		}else if("writeform".equals(action)) {		// 등록폼 -------------------
			
			//접수 
			System.out.println("등록폼 요청, 저장해줘아님");
			
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/writeForm.jsp");
			rd.forward(request, response);
						
			
		}else if ("insert".equals(action)) {		//등록 -------------------
			
			//접수 
			System.out.println("등록 요청, 데이터 3개 저장해줘");
			
			//나머지 파라미터 꺼내서 PersonVo 로  묶기
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			/* 이거 아니면 바로 밑에꺼 3개 생성자 만들기 (둘중 하나)
			PersonVo personVo = new PersonVo();
			personVo.setName(name);
			personVo.setHp(hp);
			personVo.setCompany(company);
			*/
			
			//personVo에서 3개만 있는 생성자 만들어주기
			PersonVo personVo = new PersonVo(name, hp, company);
			
			
			//Dao를 메모리에 올리기
			PhonebookDao phonebookDao = new PhonebookDao();
			
			//insertPerson(personVo) 사용해서 db에 저장
			phonebookDao.insertPerson(personVo);
			
			
			/* 리다이렉트할거임 (원래는 이거지만 반복하는것이기 때문에)
			//getPersonList() 사용해서 전체 리스트를 가져온다
			List<PersonVo> personList = phonebookDao.getPersonList();
			
			//화면그리기 --> 포워드
			//request 에 리스트 주소 넣기
			request.setAttribute("personList", personList);
			
			//포워드 (webapp에서 시작)
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp");
			rd.forward(request, response);
			*/
			
			
			//리다이렉트 (반복할사이트 넣어주기)
			//http://localhost:8080 생략가능 (http://localhost:8080/phonebook2/pbc?action=list)
			response.sendRedirect("/phonebook2/pbc?action=list");
			
			
		}else if ("editform".equals(action)) {		// 수정폼 -------------------
			
			System.out.println("수정폼 업무");
			
			// String 이면 상관없지만 숫자 int 이기 때문에 Integer.parseInt로 싸주기 
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println(no);
			
			//Dao를 메모리에 올린다
			PhonebookDao phonebookDao = new PhonebookDao();
			
			//getPersonOne(no) 로 1명 데이터의 주소를 가져온다
			PersonVo personVo =phonebookDao.getPersonOne(no);
			
			
			//화면+데이터 수정폼
			//리퀘스트 어트리부트영역에 personVo 주소를 담는다
			request.setAttribute("personVo", personVo);
			
			//포워드 editForm.jsp (webapp에서 시작)
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/editForm.jsp");
			rd.forward(request, response);
		
			
		}else if ("update".equals(action)) {		// 수정 -------------------
			
			System.out.println("수정");
			
			//파라미터 꺼내기
			int no = Integer.parseInt(request.getParameter("no"));
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			PersonVo personVo = new PersonVo(no, name, hp, company);
			//System.out.println(personVo);
			
			//phonebookDao를 메모리에 올린다
			PhonebookDao phonebookDao = new PhonebookDao();
			
			//phonebookDao를 통해서 수정 update을 시킨다
			phonebookDao.updatePerson(personVo);
			
			//리다이렉트
			response.sendRedirect("/phonebook2/pbc?action=list");
			
			
		}else if ("delete".equals(action) ) {		// 삭제 -------------------
			
			System.out.println("삭제");
			
			//파라미터 꺼내기
			// String 이면 상관없지만 숫자 int 이기 때문에 Integer.parseInt로 싸주기 (다 문자형이기 때문)
			int no = Integer.parseInt(request.getParameter("no"));
			
			//삭제하기
			//Dao를 메모리에 올린다
			PhonebookDao phonebookDao = new PhonebookDao();
			
			//phonebookDao를 통해서 삭제 delete를 시킨다
			phonebookDao.deletePerson(no);
			
			//리다이렉트 시킨다
			response.sendRedirect("/phonebook2/pbc?action=list");
			
			
		}else {					// action 없을때 -------------------
			System.out.println("action 없음");
		}
				
	}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
