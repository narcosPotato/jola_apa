<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/assetmypage.jsp" %>
<style>
.sidebar-clicked {
	background-color: #dddfeb;
}


.list tr {
	height: 40px;
}

.list tbody tr:hover {
	cursor: pointer;
	background-color: #dddfeb;
}

.list th {
	text-align: center;
	border-right: 1px solid #CCC;
}

.list tr:first-child th {
	background-color: #edf0f7;
}

.list td {
	border-bottom: 1px solid #edf0f7;
	border-right: 1px solid #edf0f7;
	text-align: center;
}

.list th:last-child, .list td:last-child {
	border-right: none;
}

.list tr td button:hover {
	background-color: #CCC;
}

button {
	border: none;
	border-radius: 5px;
	color: #858796
}

.null-msg {
	text-align: center;
}

#history-list th:nth-child(1) {
	width: 50px;
}

#history-list th:nth-child(2) {
	width: 100px;
}

#history-list th:nth-child(3) {
	width: 100px;
}

#history-list th:nth-child(4) {
	width: 100px;
}

#history-list th:nth-child(5) {
	width: 600px;
}

#history-list th:nth-child(6) {
	width: 150px;
}

#history-list th:nth-child(7) {
	width: 100px;
}

#history-list th:nth-child(8) {
	width: 270px;
}

#history-list th:nth-child(9) {
	width: 120px;
}

#history-list td:nth-child(5) {
	padding-left: 10px;
	text-align: left;
}

#pagebar {
	text-align: center;
	margin-top: 20px;
	font-size: 1.1rem;
}

.symptomNull {
	color: #CCC;
}

.status {
	color: tomato;
}

</style>
<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <%-- <%@ include file="/WEB-INF/views/inc/hospitalsidebar.jsp" %> --%>
		
		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="/apa/main.do">
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="fas fa-laugh-wink"></i>
				</div>
				<div class="sidebar-brand-text mx-3 hansans">아파! 어디가?</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Dashboard -->
			<li class="nav-item"><a class="nav-link"
				href="/apa/hospital/diagnosis/list.do" style=""> <i
					class="fas fa-fw fa-hospital-alt"></i> <span>병원 관리</span></a></li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Nav Item - Utilities Collapse Menu -->
			<li class="nav-item active">
			<a class="nav-link" href="#"
				data-toggle="collapse" data-target="#collapseUtilities1"
				aria-expanded="true" aria-controls="collapseUtilities"> <i
					class="fas fa-fw fa-stethoscope"></i> <span>진료</span>
			</a>
			<div id="collapseUtilities1" class="collapse show"
				aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
				<div class="bg-white py-2 collapse-inner rounded">
					<a class="collapse-item"
						href="/apa/hospital/diagnosis/list.do">오늘의 진료</a> <a
						class="collapse-item"
						href="/apa/hospital/diagnosis/all/register/list.do">모든 진료 예약</a>
					<a class="collapse-item sidebar-clicked"
						href="/apa/hospital/diagnosis/all/history/list.do">모든 진료 내역</a>
				</div>
			</div>
			</li>

			<!-- Nav Item - Utilities Collapse Menu -->
			<!-- <li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseUtilities2"
				aria-expanded="true" aria-controls="collapseUtilities"> <i
					class="fas fa-fw fa-calendar-check"></i> <span>건강검진</span>
			</a>
				<div id="collapseUtilities2" class="collapse"
					aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">건강검진 타입: </h6>
						<a class="collapse-item" href="/apa/hospital/medicheck/list.do">오늘의 건강검진</a> 
						<a class="collapse-item" href="/apa/hospital/medicheck/all/register/list.do">모든 건강검진 예약</a> <a
							class="collapse-item" href="/apa/hospital/medicheck/all/history/list.do">모든 건강검진 내역</a>
						<a
							class="collapse-item" href="/apa/hospital/chart/list.do">문진표</a>
					</div>
				</div></li> -->

			<!-- Nav Item - Utilities Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseUtilities3"
				aria-expanded="true" aria-controls="collapseUtilities"> <i
					class="fas fa-fw fa-user"></i> <span>고객</span>
			</a>
				<div id="collapseUtilities3" class="collapse"
					aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<a class="collapse-item" href="/apa/hospital/customer/list.do">내원환자</a>
						<a class="collapse-item" href="/apa/hospital/document/list.do">서류요청</a>
					</div>
				</div></li>

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed"
				href="/apa/hospital/advice/pick.do"> <i
					class="fas fa-fw fa-file-alt"></i> <span>의학상담</span>
			</a></li>

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed"
				href="/apa/hospital/entry/list.do"> <i
					class="fas fa-fw fa-heart"></i> <span>입점하기</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider d-none d-md-block">

		</ul>
		<!-- End of Sidebar -->


        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

					<!-- Page Heading -->
	                <div class="d-sm-flex align-items-center justify-content-between mb-4">
	                	<h1 class="h3 mb-0 text-gray-800 hansans" style="padding-top: 28px;">모든 진료 내역</h1>
	                </div>
	                    
                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <div class="topbar-divider d-none d-sm-block"></div>
                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${name}(${id}) 님</span>
                                <img class="img-profile rounded-circle"
                                    src="/apa/asset/images/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="/apa/hospital/info/view.do">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    내정보
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    로그아웃
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Content Row -->

                    <div class="row">
                        <!-- Area Chart -->
                        <div class="col-xl-12 col-lg-7">
                            <div class="card shadow mb-4">
                                <!-- Card Header - Dropdown -->
                                <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                    <h5 class="m-0 font-weight-bold text-primary">진료 내역</h5>
                                </div>
                                <!-- Card Body -->
                                <div class="card-body">
                                    <table id="history-list" class="list">
										<thead>
											<c:if test="${list.size() != 0}">
												<tr>
													<th>번호</th>
													<th>예약번호</th>
													<th>접수자</th>
													<th>환자</th>
													<th>상세증상</th>
													<th>진료과목</th>
													<th>의사</th>
													<th>진료일시</th>
													<th>진행상태</th>
												</tr>
											</c:if>
										</thead>

										<tbody>
											<c:forEach items="${list}" var="dto">
												<tr
													onclick="location.href='/apa/hospital/diagnosis/all/history/view.do?mediSeq=${dto.mediSeq}';">
													<td>${dto.rnum}</td>
													<td>${dto.mediSeq}</td>
													<td>${dto.userName}</td>
													<td>
														<c:if test="${dto.childName == null}">
															${dto.userName}
														</c:if> 
														<c:if test="${dto.childName != null}">
															${dto.childName}
														</c:if>
													</td>
													<c:if test="${dto.symptom == null}">
														<td class="symptomNull">(미작성)</td>
													</c:if>
													<c:if test="${dto.symptom != null}">
														<td>${dto.symptom}</td>
													</c:if>

													<td>${dto.departmentName}</td>
													<td>${dto.doctorName}</td>
													<td>${dto.treatmentDate}</td>
													<c:if test="${dto.waitingStatus == '진료중'}">
														<td class="status">${dto.waitingStatus}</td>
													</c:if>
													<c:if test="${dto.waitingStatus != '진료중'}">
														<td>${dto.waitingStatus}</td>
													</c:if>
													
												</tr>
											</c:forEach>
										</tbody>

									</table>


									<!-- 페이지바 -->
									<c:if test="${list.size() != 0}">
										<div id="pagebar">${pagebar}</div>
									</c:if>

									<c:if test="${list.size() == 0}">
										<h4 class="null-msg">진료 내역이 없습니다.</h4>
									</c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

			<%@ include file="/WEB-INF/views/inc/hospitalfooter.jsp" %>
			
        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <%@ include file="/WEB-INF/views/inc/hospitallogouttop.jsp" %>
</body>
</html>