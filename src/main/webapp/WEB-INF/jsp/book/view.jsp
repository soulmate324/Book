<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>도서검색</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet"
	href="https://adminlte.io/themes/AdminLTE/bower_components/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://adminlte.io/themes/AdminLTE/bower_components/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://adminlte.io/themes/AdminLTE/bower_components/Ionicons/css/ionicons.min.css">
<link rel="stylesheet"
	href="https://adminlte.io/themes/AdminLTE/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
<link rel="stylesheet"
	href="https://adminlte.io/themes/AdminLTE/dist/css/AdminLTE.min.css">
<link rel="stylesheet"
	href="https://adminlte.io/themes/AdminLTE/dist/css/skins/_all-skins.min.css">
<link rel="stylesheet"
	href="https://adminlte.io/themes/AdminLTE/plugins/iCheck/square/blue.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition register-page">
	<div class="wrapper">
		<div class="register-logo">
		 <b>Searching</b> BOOK Info
		</div>
		<!-- Main content -->
		<section class="content">
			<div class="box">
				<div class="box-body">
					<div class="input-group">
						<!-- /btn-group -->
						<input type="text" class="form-control" id="txt_query"
							value="">
						<div class="input-group-btn">
							<button type="button" class="btn btn-danger" id="btn_search">검색</button>
						</div>
					</div>

					<!-- START CUSTOM TABS -->
					<div class="nav-tabs-custom">
						<ul class="nav nav-tabs" id="tab_status">
							<li class="active"><a href="#tab_1" data-toggle="tab">Search
									List</a></li>
							<li><a href="#tab_2" data-toggle="tab">Search History</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="tab_1">
								<div class="box">
									<div class="box-header"></div>
									<!-- /.box-header -->
									<div class="box-body">
										<table id="tab1" class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>title</th>
													<th>contents</th>
													<th>publisher</th>
													<th>authors</th>
													<th>price</th>
												</tr>
											</thead>
											<tbody>
											</tbody>
											<tfoot>
												<tr><td align="center" colspan="5"><p id="pagingNavi"></p></td></tr>
											</tfoot>
										</table>
									</div>
									<!-- /.box-body -->
								</div>
								<!-- /.box -->
							</div>
							<!-- /.tab-pane -->
							<div class="tab-pane" id="tab_2">
								<div class="box">
									<div class="box-header"></div>
									<!-- /.box-header -->
									<div class="box-body">
										<table id="tab2" class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>query</th>
													<th>count</th>
													<th>date</th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
									</div>
									<!-- /.box-body -->
								</div>
								<!-- /.box -->
							</div>
							<!-- /.tab-pane -->
						</div>
						<!-- /.tab-content -->
						<!-- nav-tabs-custom -->
						<!-- /.col -->
						<!-- /.form-box -->
					</div>
				</div>
			</div>
			<!-- /.register-box -->
		</section>
	</div>

	<!-- 모달 팝업 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">로그인이 필요해요 :)</h4>
				</div>
				<div class="modal-body">
					<div>
							<div class="form-group has-feedback" id="only_reg_name" style="display:none;">
						        <input type="text" class="form-control" id="txt_name" placeholder="Nick name">
						        <span class="glyphicon glyphicon-user form-control-feedback"></span>
						    </div>
							<div class="form-group has-feedback">
								<input type="email" class="form-control" id="txt_email" placeholder="Email">
								<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
							</div>
							<div class="form-group has-feedback">
								<input type="password" class="form-control" id="txt_pass"
									placeholder="Password"> <span
									class="glyphicon glyphicon-lock form-control-feedback"></span>
							</div>
							<div class="form-group has-feedback" id="only_reg_pass" style="display:none;">
						        <input type="password" class="form-control" id="txt_pass_2" placeholder="Retype password">
						        <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
						    </div>
							<div class="row">
								<div class="col-xs-4">
								</div>
								<div class="col-xs-4">
									<button type="submit" id="btn_request_session"
										class="btn btn-primary btn-block btn-flat">로그인</button>
								</div>
							</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" id="btn_request_regist">신규등록</button> <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://adminlte.io/themes/AdminLTE/bower_components/jquery/dist/jquery.min.js"></script>
	<script
		src="https://adminlte.io/themes/AdminLTE/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script
		src="https://adminlte.io/themes/AdminLTE/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
	<script
		src="https://adminlte.io/themes/AdminLTE/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
	<script
		src="https://adminlte.io/themes/AdminLTE/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script
		src="https://adminlte.io/themes/AdminLTE/bower_components/fastclick/lib/fastclick.js"></script>
	<script
		src="https://adminlte.io/themes/AdminLTE/dist/js/adminlte.min.js"></script>
	<script src="https://adminlte.io/themes/AdminLTE/dist/js/demo.js"></script>
	<script>
		$("#document").ready(function() {
			if ('${status}' == 0) {
				$("#myModal").modal('show');
			}
			
			$("#btn_search").click(function() {
				getList(1);
			});
			
			$("#btn_request_regist").click(function() {
				if ($("#btn_request_regist").text() == "신규등록") {
					$("#only_reg_name").attr("style","display:block;");
					$("#only_reg_pass").attr("style","display:block;");
					$("#btn_request_session").text("가입하기");
					$("#btn_request_regist").text("계정있어요");
				} else {
					$("#only_reg_name").attr("style","display:none;");
					$("#only_reg_pass").attr("style","display:none;");
					$("#btn_request_session").text("로그인");
					$("#btn_request_regist").text("신규등록");
				}
				
			});
			
			$("#btn_request_session").click(function(){
				if ($("#btn_request_session").text() == "로그인") {
					$.ajax({
						url : "/api/book/login",
						type : "post",
						dataType : "json",
						data : {
							email : $("#txt_email").val(),
							passwd : $("#txt_pass").val()
						},
						success : function(res) {
							var code = res.code;
							if (code == 0) {
								$("#myModal").modal('hide');
							} else {
								alert(res.message);
							}
						},
						error : function(res) {
							alert('로그인 실패');
						}
					});
				} else {
					if ($("#txt_pass").val() == $("#txt_pass").val()) {
						$.ajax({
							url : "/api/book/signup",
							type : "post",
							dataType : "json",
							data : {
								nickName : $("#txt_name").val(),
								email : $("#txt_email").val(),
								passwd : $("#txt_pass").val()
							},
							success : function(res) {
								var code = res.code;
								if (code == 0) {
									$("#myModal").modal('hide');
								} else {
									alert(res.message);
								}
							},
							error : function(res) {
								alert('회원 가입 실패');
							}
						});
					}
				}
			});
		});

		function getList(_page) {
			$.ajax({
				url : "/api/book/search",
				type : "post",
				dataType : "json",
				data : {
					query : $("#txt_query").val(),
					page : _page
				},
				success : function(res) {
					var code = res.code;
					if (code == 0) {
						var list = res.data.documents;
						if (list != null && list.length > 0) {
							$('#tab1').find('tbody tr').remove();
							for (var i = 0; i < list.length; i++) {
								$('#tab1').find('tbody').append(
										'<tr><td><img src="'+list[i].thumbnail+'">'
												+ list[i].title + '</td><td>'
												+ list[i].contents
												+ '</td><td>'
												+ list[i].publisher
												+ '</td><td>' + list[i].authors
												+ '</td><td>' + list[i].price
												+ '</td></tr>');
							}
						}

						var meta = res.data.meta;
						var htmlStr = "";
						if (meta.page_pre > 0)
							htmlStr += "<a href='javascript:getList("
									+ meta.page_pre + ")'> prev </a>";
						for (var i = 0; i < meta.page_idx.length; i++) {
							if (meta.curr_idx != i)
								htmlStr += "<a href='javascript:getList("
										+ meta.page_idx[i] + ")'> "
										+ meta.page_idx[i] + " </a>";
							else
								htmlStr += " " + meta.page_idx[i] + " ";
						}
						if (meta.page_next > 0)
							htmlStr += "<a href='javascript:getList("
									+ meta.page_next + ")'> next </a>";
						$("#pagingNavi").html(htmlStr);
						
						var history = res.data.history;
						console.log(history);
						if (history != null && history.length > 0) {
							$('#tab2').find('tbody tr').remove();
							for (var i = 0; i < list.length; i++) {
								$('#tab2').find('tbody').append(
										'<tr><td>'
												+ history[i].query + '</td><td>'
												+ history[i].searchCount + '</td><td>'
												+ history[i].created
												+ '</td></tr>');
							}
						}
					} else {
						alert(res.message);
					}
				},
				error : function(res) {
					console.log(res);
					alert('검색 도중 오류가 발생하였습니다.');
				}
			});
		}

		$("#tab_status a").click(function(e) {
			e.preventDefault();
			$(this).tab("show");
			var target = $(e.target).attr("href") // activated tab
			if (target == "#tap_1") {
				getList(0);
			} else {
				getList(1);
			}
		});
	</script>
</body>
</html>