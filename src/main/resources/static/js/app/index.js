let main = {
    init() {
      let _this = this;
      $("#btn_save").on("click", () => {
          _this.save();
      });
      $("#btn_update").on("click", () => {
            _this.update();
      });
      $('#btn_delete').on('click', () => {
            _this.delete();
      });
    },
    save() {
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
            data: JSON.stringify(data)
        })
            .done(() => {
                alert('Success');
                window.location.href= '/';
            })
            .fail((data) => {
                alert(JSON.stringify(data));
            });
    },
    update() {
        let data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        let id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/post/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        })
            .done(() => {
                alert('Success');
                window.location.href = '/';
            })
            .fail((data) => {
                alert(JSON.stringify(data));
        });
    },
    delete() {
        let id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/post/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        })
            .done(() => {
                alert('Success');
                window.location.href = '/';
            })
            .fail((data) => {
                alert(JSON.stringify(data));
            });
    }
}

main.init();