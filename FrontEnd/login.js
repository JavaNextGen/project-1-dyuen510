const url = 'http://localhost:3000/';
const localurl = 'file:///C:/Project/project-1-dyuen510/FrontEnd/';

$('#getUsersButton').on('click', showUsers);


// async function showUsers() {
//     const result = await $.ajax({
//         url: url + 'users',
//         type: 'GET',
//         xhrfields:{
//             withCredentials: true
//         },
//         crossDomain: true
//     }).then(function(res){
//         console.log(res);
//     })
// }

$('#loginButton').on('click', loginFunction);

async function showUsers() {
    let response = await fetch(url + 'users', {credentials:'include'});

    console.log(response);

    if(response.status === 200){

        let data = await response.json();

        console.log(data);
    }
}
async function loginFunction() {
    let usern = $('#username').val();
    let userp = $('#password').val();

    let user = {
        username: usern,
        password: userp
    };

    console.log(user);

    let response = await fetch (url + 'login', {
        method: 'POST',
        body: JSON.stringify(user),
        credentials: 'include' //this line ensures the cookie is captured so that we can have a user session
    });

    console.log(response.status);
};

const signUp =() => {
    window.location.href = localurl + 'signup.html';
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