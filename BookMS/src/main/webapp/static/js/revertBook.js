function data(id) {
    $.ajax({
        url:"/updatestatus",
        type:"get",
        dataType:"json",
        data:{id:id},
        success:function (data) {
            if(data.code=="0000");
            alert("还书成功");
        },
        error:function () {
            alert("还书失败")
        }
    })

}