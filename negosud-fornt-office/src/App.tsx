import './App.css'
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Panier from "./pages/Panier.tsx";
import Accueil from "./pages/Accueil.tsx";
import Menu from "./composants/Menu.tsx";
import Connexion from "./pages/Connexion.tsx";
import Inscription from "./pages/Inscription.tsx";


// Composant principal avec le router
function App() {
    return (
        <Router>
            <Menu/>
            <Routes>
                <Route path="/" element={<Accueil />} />
                <Route path="/panier" element={<Panier />} />
                <Route path="/connexion" element={<Connexion />} />
                <Route path="/inscription" element={<Inscription />} />
            </Routes>
        </Router>
    );
}

export default App;
