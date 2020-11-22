<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="layui-side layui-bg-black">
	<div class="layui-side-scroll">
		<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
		<ul class="layui-nav layui-nav-tree" lay-filter="test">
			<li class="layui-nav-item layui-nav-itemed">
				<dl class="layui-nav-child">
					<dd>
						<a href="revertbook">我要还书</a>
					</dd>

					<dd>
						<a href="showBook">我要借书</a>
					</dd>
					<dd>
						<a href="showmemberrecords">我的阅读记录</a>
					</dd>
					<dd>
						<a href="newBookList">最新图书列表</a>
					</dd>
					<dd>
						<a href="deleteBookList">下架图书列表</a>
					</dd>

				</dl></li>
			<%--<li class="layui-nav-item"><a href="javascript:;">解决方案</a>
				<dl class="layui-nav-child">
					<dd>
						<a href="javascript:;">列表一</a>
					</dd>
					<dd>
						<a href="javascript:;">列表二</a>
					</dd>
					<dd>
						<a href="###">超链接</a>
					</dd>
				</dl></li>
			<li class="layui-nav-item"><a href="###">云市场</a></li>
			<li class="layui-nav-item"><a href="###">发布商品</a></li>--%>
		</ul>
	</div>
</div>