import {useEffect, useState} from "react";
import {Link} from "react-router-dom";

export default function Panier() {
    const [ligneCommandes, setLigneCommandes] = useState([]);

    useEffect(() => {
        raffraichir();
    }, []);

    function raffraichir() {
        fetch("http://localhost:8080/api/commande/panier",{
            headers:{
                Authorization: 'Bearer ' + localStorage.getItem('token')
            }
        })
            .then(res => res.json())
            .then(commande => setLigneCommandes(commande.ligneCommandes));
    }

    function supprimerLigne(idLigne: number) {
        fetch(`http://localhost:8080/api/ligne-commande/${idLigne}`, { method: "DELETE" })
            .then(() => raffraichir());
    }

    return (
        <div>
            <h1>Panier</h1>
            <Link to="/">Retour à l'accueil</Link>
            <ul>
                {ligneCommandes.map((ligne: any) => (
                    <li key={ligne.id}>
                        <span>{ligne.produit.nom}</span>
                        <button onClick={() => supprimerLigne(ligne.id)}>Supprimer</button>
                    </li>
                ))}
            </ul>
        </div>
    );
}