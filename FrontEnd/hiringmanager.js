const url = 'http://localhost:3000/';
const localurl = 'file:///C:/Project/project-1-dyuen510/FrontEnd/';
const user = window.localStorage.getItem(1);
console.log(user);
// $(document).ready(getByUsername());


$('#getAllEmployees').on('click', getEmployees);
// $('#verifyReim').on('click', verifyReimbursements);
$('#history').on('click', getHistory);
$('#newReim').on('click', submitNewReim);
$('#statusInput').on('click', statusChosen);
$('#reimIdSubmit').on('click', locateTicket);
$('#choiceInput').on('click', verify);


let reimbursementId; // need
let reimUserId;
const userId = window.localStorage.getItem(2);

const logOut = () => {
    window.localStorage.clear();
    window.location.href = localurl + 'login.html';
}

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


async function getEmployees(){
    
    let response = await fetch(url + 'users',{
    method:'GET',
    body:JSON.stringify(),
    credentials:'include'
})
    console.log(response);
    
    if(response.status === 200){
        let data = await response.json();

        console.log(data);

    }
}


// const userId = window.localStorage.getItem(2);
//     console.log(userId);
// reimbursementhistory

async function getHistory() {
    getByUsername();
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

console.log('hello');
//status 
async function statusChosen(e){
    e.preventDefault();
    
    // var select = $('#status');
    // var value = $('option[name="stat"]:selected').val();
    
    let select = $('#status').val();
    console.log(select);
    let response = await fetch(url + 'reimbursementsByStatus/' + select, {
        method:'GET',
        body: JSON.stringify(),
        credentials: 'include'
    });
    console.log(response);
    console.log(response.status);
    if(response.status === 200) {
        let data = await response.json();
        console.log(data);
    }
}
async function locateTicket(){
    reimbursementId = $('#reimId').val(); // need
    let response = await fetch(url + 'reimbursementById/' + reimbursementId, {
        method: 'GET',
        body: JSON.stringify(),
        credentials: 'include'
    });
    console.log(response);
    console.log(response.status);
    if(response.status === 200) {
        let data = await response.json();
        reimUserId = data.value.resolver.id;
        reimStatusId = data.value.status_fkey;
        console.log(reimUserId, reimStatusId);
        console.log(data);
    }

} 

async function verify(e){
    if(userId != reimUserId && reimStatusId == 1){
    e.preventDefault();
    
    let chosen = $('#choices').val();
    let newStatusInt;

    if(chosen == 'APPROVED'){
        newStatusInt = 2;
    }else if(chosen == 'DENIED'){
        newStatusInt = 3;
    }else{
        console.log('ticket already has been processed');
        alert('processed ticket cannot be updated.')
    }
    let userParseInt = parseInt(userId);
    let reimParseInt = parseInt(reimbursementId);

    let updateInfo = {
        status_fkey : newStatusInt,
        user_fkey_resolved : userParseInt,
        id : reimParseInt
    }
    console.log(updateInfo);
    let response = await fetch(url + 'reimbursement', {
        method: 'PUT',
        body: JSON.stringify(updateInfo),
        credentials: 'include'
    });
    if(response.status === 200) {
        console.log("successfully updated");
    }
}
}