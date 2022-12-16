<!DOCTYPE html>
<html>
    <head>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:80%;">
            <#-- 用户ID -->
            <input name="uId" type="hidden" value="${(user.uId)!}"/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userName"
                           lay-verify="required" name="uName" id="uName"  value="${(user.uName)!}" placeholder="请输入用户名">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">手机号</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userEmail"
                           lay-verify="required" name="uPhone" value="${(user.uPhone)!}" id="uPhone" placeholder="请输入手机号">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userEmail"
                           lay-verify="required" name="uEmil"  value="${(user.uEmil)!}" id="uEmil" placeholder="请输入邮箱">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">部门专业</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input uProfession"
                           lay-verify="required" name="uProfession" value="${(user.uProfession)!}" id="uProfession" placeholder="请输入部门专业名称">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">用户身份</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input uProfession"
                           lay-verify="required" name="cId" value="${(user.cId)!}" id="uProfession" placeholder="请输入用户身份">
                </div>
            </div>



            <#--<div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">角色</label>
                <div class="layui-input-block">
                    <select name="roleIds"  xm-select="selectId">
                    </select>
                </div>
            </div>-->


            <br/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-lg" lay-submit=""
                            lay-filter="addOrUpdateUser">确认
                    </button>
                    <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
                </div>
            </div>
        </form>

    <script type="text/javascript" src="${ctx}/js/user/add.update.js"></script>
    </body>
</html>