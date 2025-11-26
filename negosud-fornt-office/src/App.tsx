import {useEffect, useState} from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {

  const [ligneCommandes, setLigneCommandes] = useState([]);

  //on effectue la requete pour les ligne du panier une seule fois
    useEffect(() => {

            fetch("http://localhost:8080/api/commande/panier")
                .then(res => res.json())
                .then(commande => setLigneCommandes(commande.ligneCommandes))

        },  []);

  return (
    <>
      <h1>Panier</h1>
      <ul>
          {
              ligneCommandes.map((ligne: any) => <li key={ligne.id}>{ligne.produit.nom}</li>)
          }
      </ul>
    </>
  )
}

export default App
