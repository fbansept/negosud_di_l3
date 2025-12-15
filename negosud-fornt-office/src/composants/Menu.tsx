import {Link} from "react-router-dom";

type MenuProps = { connecte: boolean, setConnecte: Function }

export default function Menu({connecte, setConnecte} : MenuProps) {

    function onDeconnexion() {
        localStorage.removeItem('token');
        setConnecte(false)
    }

    return (
        <nav className="navbar navbar-expand-lg bg-body-tertiary">
            <div className="container-fluid">
                <a className="navbar-brand" href="#">Navbar</a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">

                        <li className="nav-item">
                            <Link className="nav-link" to="/">Accueil</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to="/panier">Panier</Link>
                        </li>

                        {
                            connecte ? (
                                    <li className="nav-item">
                                        <Link onClick={() => onDeconnexion()} className="nav-link" to="/connexion">
                                            Se déconnecter
                                        </Link>
                                    </li>)
                                : (
                                    <>
                                        <li className="nav-item">
                                            <Link className="nav-link" to="/connexion">Connexion</Link>
                                        </li>
                                        <li className="nav-item">
                                            <Link className="nav-link" to="/inscription">Inscription</Link>
                                        </li>
                                    </>
                                )
                        }

                    </ul>
                    <form className="d-flex" role="search">
                        <input className="form-control me-2" type="search" placeholder="Search"
                               aria-label="Search"/>
                        <button className="btn btn-outline-success" type="submit">Search</button>
                    </form>
                </div>
            </div>
        </nav>

    );
}