let main = {
    init : function () {
      let _this = this;
      $("#btn_save").on("click", function () {
          _this.save();
      });
      $("#btn_update").on("click", function () {
            _this.update();
      });
      $('#btn_delete').on('click', function () {
            _this.delete();
      });
    },
    save : function () {
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
            author: $("#author").val(),
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/post',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.parse(data)
        })
            .done(function () {
                alert('Success');
                window.location.href= '/';
            })
            .fail(function () {
                alert(JSON.stringify(error));
            });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/post/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        })
            .done(function() {
                alert('Success');
                window.location.href = '/';
            })
            .fail(function (error) {
                alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/post/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        })
            .done(function() {
                alert('Success');
                window.location.href = '/';
            })
            .fail(function (error) {
                alert(JSON.stringify(error));
            });
    }
}

main.init();