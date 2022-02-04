const url = 'http://localhost:3000/';
const localurl = 'file:///C:/Project/project-1-dyuen510/FrontEnd/';

$('#signup').on('click', createFunction);

function Home() {
    window.location.href = localurl + 'login.html';
}


async function createFunction(e){
    e.preventDefault();
    $('#home').show();
    $('#signuptext').hide();
    // let response = await fetch(url + 'username', {credentials:'include'});

    let usern = $('#username').val();
    let passwordp = $('#password').val();
    let fname = $('#f_name').val();
    let lname = $('#l_name').val();
    let email = $('#email').val();
    let role = $('input[name="role"]:checked').val();
    let roleFkey;

    if(role === 'employee'){
        roleFkey = 1;
    }else if(role === 'finance_manager'){
        roleFkey = 2;
    }

    // console.log(role);

    // if(role =='')

    let user = {
        username : usern,
        password : passwordp,
        f_name : fname,
        l_name : lname,
        email : email,
        user_role_fkey : roleFkey
    };
    console.log(user);
    console.log(roleFkey);

    let response = await fetch (url + 'username', {
        method: 'POST',
        body: JSON.stringify(user),
        credentials:'include'
    });

    console.log(response.status);
    alert('Sign up complete!')
    //instead of alert have a popup that allows user to be redirect to home page
    
};