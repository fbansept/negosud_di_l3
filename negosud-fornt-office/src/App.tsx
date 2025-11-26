import './App.css'

// App.jsx (ou App.tsx)
import { useEffect, useState } from 'react';
import {BrowserRouter as Router, Routes, Route, Link} from 'react-router-dom';
import './App.css';

// Composant pour le menu de navigation
function Menu() {
    return (
        <nav style={{ backgroundColor: '#f0f0f0', padding: '10px', marginBottom: '20px' }}>
            <Link to="/" style={{ marginRight: '15px' }}>Accueil</Link>
            <Link to="/panier">Panier</Link>
        </nav>
    );
}

// Composant pour la page d'accueil
function Accueil() {
    return (
        <div>
            <h1>Bienvenue sur notre site !</h1>
            <p>Ceci est la page d'accueil.</p>
            <Link to="/panier">Voir le panier</Link>
        </div>
    );
}

// Composant pour la page panier
function Panier() {
    const [ligneCommandes, setLigneCommandes] = useState([]);

    useEffect(() => {
        raffraichir();
    }, []);

    function raffraichir() {
        fetch("http://localhost:8080/api/commande/panier")
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

// Composant principal avec le router
function App() {
    return (
        <Router>
            <Menu/>
            <Routes>
                <Route path="/" element={<Accueil />} />
                <Route path="/panier" element={<Panier />} />
            </Routes>
        </Router>
    );
}

export default App;
