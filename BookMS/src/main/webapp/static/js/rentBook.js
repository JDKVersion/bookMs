function data(id) {
    $.ajax({
        url:"/rentbook",
        type:"get",
        dataType:"json",
        data:{id:id},
        success:function (data) {
            console.log(data);
            if(data.code=="0000"){
                alert("借书成功");
            }else if(data.code=="0001"){
                alert("此书您尚未归还，请归还后再借阅")
            };

        },
        error:function () {
          alert("借书失败")
        }
    })

}