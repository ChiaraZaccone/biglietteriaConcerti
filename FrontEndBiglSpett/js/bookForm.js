function Biglietti(idSpettacolo, codCliente, tipoPagamento, qtBiglietti) {

    this.idSpettacolo = idSpettacolo;
    this.codCliente = codCliente;
    this.tipoPagamento = tipoPagamento;
    this.qtBiglietti = qtBiglietti;
    
    

}

const btn = document.getElementById('btnBook');

btn.addEventListener('click', createBooking);

function createBooking() {

    //recupero i dati dalla session storage
    let utConnessoJSON = localStorage.getItem("data");
    let utConnesso = JSON.parse(utConnessoJSON)
    console.log(utConnesso)
    //prendo i dati che mi mancano dal form
    let codCliente = utConnesso.codCliente;
    let idSpettacolo = document.getElementById('inputIdSpettacolo').value;
    let inputPagamento = document.getElementById('inputPagamento').value;
    let inputNumBiglietti= document.getElementById('inputNumBiglietti').value;

    // Biglietti(idSpettacolo, codCliente, tipoPagamento, qtBiglietti, prezzo) {

    let biglietti = new Biglietti(idSpettacolo, codCliente, inputPagamento, inputNumBiglietti);

    const dataSend = JSON.stringify(biglietti);

    fetch('http://localhost:8080/biglietteriaspettacoli/biglietto', {
        method: 'POST', headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }, body: dataSend
    })
    .then(response => {
        if(response.status == 201) {
            alert('hai acquistato un articolo!')
        }else {
            response.json()
            .then(data => {
                
                alert(data.message)})
           
        }
    });

}
//PUT

function BigliettiPut(id, idSpettacolo, codCliente, tipoPagamento, qtBiglietti) {

    this.id = id;
    this.idSpettacolo = idSpettacolo;
    this.codCliente = codCliente;
    this.tipoPagamento = tipoPagamento;
    this.qtBiglietti = qtBiglietti;
    
    

}



const btnChange = document.getElementById('btnChange');

btnChange.addEventListener('click', changeBooking);

function changeBooking() {

    //recupero i dati dalla session storage
    let BigliettiJSON = localStorage.getItem("Biglietti");
    let utBiglietti = JSON.parse(BigliettiJSON)
    let utConnessoJSON = localStorage.getItem("data");
    let utConnesso = JSON.parse(utConnessoJSON)
    
    //prendo i dati che mi mancano dal form
    let inputModNumBiglietti = document.getElementById('inputModNumBiglietti').value;
    let codCliente = utConnesso.codCliente;
    

    console.log(utBiglietti);
    for(let i=0;i<utOrdini.length;i++){
        if(utOrdini[i].id == idOrdine){
cod_articolo = utOrdini[i].cod_articolo;
        }
    }
    
    let inputPagamento = utOrdini.tipo_pagamento;
    let inputModNumArticoli= document.getElementById('inputModNumArticoli').value;

    let carrello = new CarrelloPut(idOrdine,cod_cliente, inputPagamento, inputModNumArticoli, cod_articolo);

    const dataSend = JSON.stringify(carrello);

    fetch('http://localhost:8080/acquistionline/carrello', {
        method: 'PUT', headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }, body: dataSend
    })
    .then(response => {
        console.log(response.status)
        if(response.status == 200) {
            alert('Modifica effettuata!')
        }else if(response.status==500){
            alert('articolo non disponibile')
        }
    });

}