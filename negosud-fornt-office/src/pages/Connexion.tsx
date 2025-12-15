import {useState} from "react";
import {useForm} from "react-hook-form";

export default function Connexion() {

    const {
        register,
        handleSubmit,
        watch,
        formState: {errors},
    } = useForm<{ email: string, password: string }>()


    function login(utilisateur: any) {

        fetch("http://localhost:8080/connexion", {
            method: 'POST',
            body: JSON.stringify(utilisateur),
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(res => res.text())
            .then(jwt => localStorage.setItem('token', jwt))
    }

    return (
        <div>
            <h1>Connexion</h1>

            <form onSubmit={handleSubmit((data) => login(data))}>
                <div className="mb-3">
                    <label htmlFor="email" className="form-label">Email address</label>
                    <input type="text" className="form-control" id="email"
                           placeholder="name@example.com"
                           {...register("email", {
                               required: {
                                   value: true,
                                   message: "L'email est obligatoire"
                               },
                               pattern: {
                                   value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                                   message: "Format email incorrect"
                               }
                           })}/>

                    {
                        errors.email ? <div className="form-error">
                            {errors?.email?.message}
                        </div> : null
                    }


                </div>
                <div className="mb-3">
                    <label htmlFor="password" className="form-label">Password</label>
                    <input type="password" id="password" className="form-control"
                           aria-describedby="passwordHelpBlock" {...register("password", {required: true})}/>
                    {errors.password && <div className="form-error">
                        Le mot de passe est obligatoire
                    </div>}
                    <div id="passwordHelpBlock" className="form-text">
                        Your password must be 8-20 characters long, contain letters and numbers, and must not contain
                        spaces,
                        special characters, or emoji.
                    </div>
                </div>

                <button className="btn btn-primary" type="submit">Se connecter</button>
            </form>
        </div>
    )
}
