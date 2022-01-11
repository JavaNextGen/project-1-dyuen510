const url = 'http://localhost:3000/';

$('#getUsersButton').on('click', showUsers);


async function showUsers() {
    const result = await $.ajax({
        url: url + 'users',
        method: 'GET'
    }).then(function(res){
        console.log(res);
    })
}

$('#loginButton').on('click', loginFunction);

async function loginFunction() {
    let usern = $('#username').val();
    let userp = $('#password').val();

    let user = {
        username: usern,
        password: userp
    };

    console.log(user);
    let response = await $.ajax({
        url: url + 'login',
        method: 'POST',
        body: JSON.stringify(user),
        credentials: 'include'
    }).then(function(res){
        console.log(res);
    })
};

// async function loginFunction() {
//     console.log('hello');
// };


// async function loginFunction(){
//     let usern = $('#username').val();
//     let userp = $('#password').val();

//     let user = {
//         username:usern,
//         password:userp
//     };

//     console.log(user);

//     let response = await fetch (url + 'users');

//         console.log(response);
//     //     method: "POST",
//     //     body: JSON.stringify(user),
//     //     credentials: 'include'
//     // });

//     // console.log(response.status);


// };