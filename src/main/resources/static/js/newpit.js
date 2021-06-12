
$(function parseInfo() {
    $('body').on('click', '#parse', function () {
        // let username = $(this).data("username");
        let latitude = $("#latitude").val();
        let longitude = $("#longitude").val();
        let velocity = $("#velocity").val();
        let date = $("#date").val();
        console.log(latitude, longitude, velocity, date);
        $.ajax({
            type: "POST",
            url: "/api/add/",
            dataType: "json",
            data: {
                latitude: latitude,
                longitude: longitude,
                velocity: velocity,
                date: date
            },
            success: function(result) {
                alert('ok');
            },
            error: function(result) {
                alert('error');
            }
        }).done((msg) => {
            console.log(msg)
        })
    })
});


