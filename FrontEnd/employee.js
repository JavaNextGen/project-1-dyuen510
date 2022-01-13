const url = 'http://localhost:3000/';
const localurl = 'file:///C:/Project/project-1-dyuen510/FrontEnd/';

$('#history').on('click', getHistory);
$('#newReim').on('click', submitNewReim);

const logOut = () => {
    window.localStorage.clear();
    window.location.href = localurl + 'login.html';
}


// async function showUsers() {
//     //need to grab the user id somehow and f_name
//     let response = await fetch(url + '/reimbursements/history/{user_id}')
// }

const user = window.localStorage.getItem(1);
console.log(user);

//get by username | user's information
async function getByUsername() {

    let response = await fetch(url + 'username/' + user, {
        method: 'GET',
        body: JSON.stringify(),
        credentials: 'include'
    })
    console.log(response.status);

    if(response.status === 200){
        let data = await response.json();
        console.log(data.value.id);
        window.localStorage.setItem(2, data.value.id);
    }
}
const userId = window.localStorage.getItem(2);
console.log(userId);
// reimbursementhistory

async function getHistory() {
    let response = await fetch(url + 'reimbursements/history/' + userId, {
        method:'GET',
        body: JSON.stringify(),
        credentials: 'include'
    })

    console.log(response.status);
    if(response.status === 200) {
        let data = await response.json();
        console.log(data);
    }
}

//submit new reimbursement
async function submitNewReim(e) {
    e.preventDefault();

    let amount = $('#amount').val();
    let description = $('#description').val();
    let type = $('input[name="type"]:checked').val();
    console.log('hello');

    let reimbursement = {
        amount : amount,
        description : description,
        type_id : type,
        user_fkey_author : userId
    };

    console.log(reimbursement);

    let response = await fetch(url + 'reimbursement', {
        method: 'POST',
        body: JSON.stringify(reimbursement),
        credentials: 'include'
    });

    console.log(response.status);

}





// window.onload = getByUsername;