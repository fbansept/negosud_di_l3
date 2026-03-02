
import {Link} from "react-router-dom";

export default function Accueil() {

    return (
        <div>
            <h1>Bienvenue sur notre site !</h1>
            <p>Ceci est la page d'accueil.</p>
            <Link to="/panier">Voir le panier</Link>
        </div>
    );
}