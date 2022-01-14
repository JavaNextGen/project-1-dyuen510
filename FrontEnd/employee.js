const url = 'http://localhost:3000/';
const localurl = 'file:///C:/Project/project-1-dyuen510/FrontEnd/';

$('#welcome').append(' ' + window.localStorage.getItem(1));
$('#welcome').on('click', function(){
    location.reload();
})

$('#history').on('click', getHistory);
$('#newReim').on('click', submitNewReim);

$('#submitForm').on('click', function(){
    $('#submitNew').show();
})

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
const userId = window.localStorage.getItem(2);
console.log(userId);


// async function showUsers() {
//     //need to grab the user id somehow and f_name
//     let response = await fetch(url + '/reimbursements/history/{user_id}')
// }

const user = window.localStorage.getItem(1);
console.log(user);


//get by username | user's information

// reimbursementhistory

async function getHistory() {
    $('#historyBlock').show();
    let response = await fetch(url + 'reimbursements/history/' + userId, {
        method:'GET',
        body: JSON.stringify(),
        credentials: 'include'
    })

    console.log(response.status);
    if(response.status === 200) {
        let data = await response.json();
        console.log(data);

        for(let history of data){
            let row = document.createElement('tr');
            if(history.status=='PENDING'){
                row.setAttribute('class', 'table-default')
            }else if(history.status=='APPROVED'){
                row.setAttribute('class', 'table-primary')
            }else{
                row.setAttribute('class', 'table-danger')
            }
            // let row = document.createElement('tr');
            let cell = document.createElement('td');
    
            cell.innerHTML = history.id;
            row.appendChild(cell);
    
            let cell2 = document.createElement('td');
            cell2.innerHTML = history.amount;
            row.appendChild(cell2);
    
            let cell3 = document.createElement('td');
            console.log(history.date_submitted);
            cell3.innerHTML = history.status;
            row.appendChild(cell3);
    
            let cell4 = document.createElement('td');
            cell4.innerText = history.date_submitted;
            row.appendChild(cell4);
    
            let cell5 = document.createElement('td');
            cell5.innerHTML = history.user_fkey_resolved;
            row.appendChild(cell5);
    
            $('#historyBody').append(row);
    
        }

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

    console.log(response);
    alert('reimbursement amount : $' + reimbursement.amount + ' for ' + reimbursement.description + ' has been submitted.')
}





// window.onload = getByUsername;