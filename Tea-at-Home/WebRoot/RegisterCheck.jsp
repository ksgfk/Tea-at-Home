<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" import="java.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">

	<title>Tea At Home 注册</title>

	<link type="text/css" rel="stylesheet" href="./css/base.min.css">
	<link type="text/css" rel="stylesheet" href="./css/notify.min.css">
	<style type="text/css">
		#code {
			text-transform: uppercase;
		}

		.actions .btn {
			width: 130px;
		}

		#app label {
			width: 50px;
		}
	</style>
	<style type="text/css">
		.background {
			/* filter: alpha(opacity=30);
            -moz-opacity: 0.3;
            opacity: 0.3; */
			background-color: azure;
			background-image: url(./css/BackGround.png);
			background-repeat: round;
		}
	</style>
	<script src="./css/gt.js.下载"></script>
	<script src="./css/jquery.min.js.下载"></script>


	<script src="./css/base.js.下载"></script>
	<script>
		$(function() {
			$('#register').click(function() {
				if (!$('#username').val() || !$('#password').val() || !$('#code').val()) {
					notify.warning('错误', '请输入用户名、密码和验证码');
					return;
				}
				if ($('#username').val().length > 20) {
					notify.warning('错误', '用户名过长');
					return;
				}
				if ($('#password').val().length < 8) {
					notify.warning('错误', '密码过短');
					return;
				}

				$(this).attr('disabled', true).html('提交中...');
				window.location.href = 'RegisterCheck.jsp';
				// 你问为什么要验证两次¿ 我喜欢不行么
				callGeetest(captcha => $.post('/ajax/register', {
					mail: $('#mail').val(),
					code: $('#code').val(),
					username: $('#username').val(),
					password: $('#password').val(),
					c: captcha.geetest_challenge,
					v: captcha.geetest_validate,
					s: captcha.geetest_seccode
				}, function(data) {
					if (data.success) {
						notify.success('注册成功');
						location.href = '/redirect';
					} else {
						notify.error('注册失败', data.message);
						$('#register').attr('disabled', false).html('注册');
					}
				}, 'json').fail(function() {
					$('#register').attr('disabled', false).html('注册');
					notify.error('注册失败', '网络错误或服务器出现错误, 请重试');
				}), () => $('#register').attr('disabled', false).html('注册'));
			});
		});
	</script>
	<!-- <script charset="UTF-8" async="" src="./rig_files/gettype.php"></script> -->
	<script charset="UTF-8" async="" crossorigin="anonymous" src="./css/fullpage.9.0.5.js.下载"></script>
	<script charset="UTF-8" src="./css/get.php"></script>
	<link href="./css/style_https.1.5.8.css" rel="stylesheet">
</head>

<body>
<div id="app" class="login background">
	<div class="single-bg"></div>
	<div class="notify-container"></div>
	<div class="main padding-limiter">
		<div class="panel login-panel">
		<div class=" double-column ">
			<img class=" panel-wide login-img " src=" ./css/erciyuanzhenexin.jpg " title=" " style=" ">
			<div class=" panel-narrow ">
				<div class=" title ">
					TeaAtHome 注册
				</div>
				<div class=" control-group ">
					<label>用户名</label>
					<div class=" controls ">
						<input id=" username " type=" text " placeholder=" 1222 " maxlength=" 20 " title=" 用户名 ">
					</div>
				</div>
				<div class=" control-group ">
					<label>密　码</label>
					<div class=" controls ">
						<input id=" password " type=" password " placeholder=" 123123 " title=" 密码 ">
					</div>
				</div>
				<div class=" control-group ">
					<label>性　别</label>
					<div class=" controls ">
						<input id=" sex " type=" text " placeholder=" 123123 " maxlength=" 6 " title=" 性别 ">
					</div>
				</div>
				<div class=" control-group ">
					<label>手机号</label>
					<div class=" controls ">
						<input id=" phone " type=" password " placeholder=" 标准312321312手机号 " maxlength=" 128 " title=" 手机号 ">
					</div>
				</div>
				<div class=" control-group ">
					<label style=" width: 100%;word-break: break-all; ">
						*在Tea-At-Home注册就代表你是一个二次元*
					</label>
				</div>

				<div class=" actions ">


					<button class=" btn btn-green " id="register">注册</button>
					<button class=" btn"  id="back ">返回</button>
				</div>
			</div>
		</div>
	</div>
</div>
</div>



<div class="geetest_panel geetest_wind " style="display: none; ">
	<div class="geetest_panel_ghost "></div>
	<div class="geetest_panel_box ">
		<div class="geetest_other_offline geetest_panel_offline "></div>
		<div class="geetest_panel_loading ">
			<div class="geetest_panel_loading_icon "></div>
			<div class="geetest_panel_loading_content ">智能验证检测中</div>
		</div>
		<div class="geetest_panel_success ">
			<div class="geetest_panel_success_box ">
				<div class="geetest_panel_success_show ">
					<div class="geetest_panel_success_pie "></div>
					<div class="geetest_panel_success_filter "></div>
					<div class="geetest_panel_success_mask "></div>
				</div>
				<div class="geetest_panel_success_correct ">
					<div class="geetest_panel_success_icon "></div>
				</div>
			</div>
			<div class="geetest_panel_success_title ">通过验证</div>
		</div>
		<div class="geetest_panel_error ">
			<div class="geetest_panel_error_icon "></div>
			<div class="geetest_panel_error_title ">网络超时</div>
			<div class="geetest_panel_error_content ">请点击此处重试</div>
			<div class="geetest_panel_error_code ">
				<div class="geetest_panel_error_code_text "></div>
			</div>
		</div>
		<div class="geetest_panel_footer ">
			<div class="geetest_panel_footer_logo "></div>
			<div class="geetest_panel_footer_copyright ">由居茶有限公司提供技术支持</div>
		</div>
		<div class="geetest_panel_next "></div>
	</div>
</div>
</body>
<div style="all: initial; ">
	<div id="__hcfy__ " style="all: initial; "></div>
</div>

</html>
