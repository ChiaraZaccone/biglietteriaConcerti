const btnSpettacoli = document.getElementById('btnSpettacoli');

function mostraTabellaSpettacoli(){
    fetch('http://localhost:8080/biglietteriaspettacoli/spettacoli')
    .then(response => response.json())
    .then(data => { 
        let table = `
            <tr>
                <th>Codice spettacolo</th>
                <th>Artista</th>
                <th>Data</th>
                <th>Orario</th>
                <th>Città</th>
                <th>Luogo</th>
                <th>Prezzo</th>
               
            </tr>
        `;
    
        for(let i = 0; i < data.length; i++) {
    
            table +=`
                <tr>
                    <td>${data[i].idSpettacolo}</td>
                    <td>${data[i].artista}</td>
                    <td>${data[i].dataSpettacolo}</td>
                    <td>ore 20.00</td>
                    <td>${data[i].citta}</td>
                    <td>${data[i].luogo}</td>
                    <td>${data[i].prezzo}€</td>
  
  
  
  
                   
                </tr>
            `;
        }
        
        let tabella = document.getElementById('tableSpettacoli')
        tabella.classList.add('nascondi');
        tabella.innerHTML = table;
    })
  }

btnSpettacoli.addEventListener('click',mostraTabellaSpettacoli)


const btnLogin = document.getElementById('btnLogin');

function checkCodCliente() {

    const cod_cliente = document.getElementById('inputUserCode').value;
    console.log(cod_cliente);
    fetch('http://localhost:8080/biglietteriaspettacoli/cliente/' + cod_cliente)
    .then(response =>{
        if(response.status == 200){
            response.json()

            .then(data => {
                let utLoggato = JSON.stringify(data);
                localStorage.setItem("data",utLoggato);
                window.location.replace('./bookForm.html');
            })
        }else{
            alert('Mi dispiace, utente non registrato!');
        }
    })
}

btnLogin.addEventListener('click', checkCodCliente);




