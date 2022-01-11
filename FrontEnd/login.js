const url = 'https://localhost:3000/';

$('#loginButton').on('click', loginFunction);

async function loginFunction() {
    console.log('hello');
};


async function loginFunction(){
    let usern = $('#username').val();
    let userp = $('#password').val();

    let user = {
        username:usern,
        password:userp
    };

    console.log(user);

    let response = await fetch (url + 'username/{username}', {

        method: "POST",
        body: JSON.stringify(user),
        credentials: 'include'
    });

    console.log(response.status);


};