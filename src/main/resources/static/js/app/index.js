let main = {
    init : function () {
      let _this = this;
      $("#btn_save").on("click", function () {
          _this.save();
      });
    },
    save : function () {
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
            author: $("#author").val(),
        };

        $.ajax({
            type: "POST",
            url: "/api/v1/post",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: JSON.parse(data)
        }).done(function () {
            alert("Success");
            window.location.href= "/";
        }).fail(function () {
            alert(JSON.stringify(error));
        });
    }
}

main.init();