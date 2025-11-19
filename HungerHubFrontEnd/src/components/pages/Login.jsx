import { useEffect } from "react"
import style from '../css/login.module.css'
function Login() {
    useEffect(() => {
        const body = document.body.style;

        body.background = "linear-gradient(135deg, #667eea, #764ba2)";
        body.minHeight = "100vh";
        body.display = "flex";
        body.alignItems = "center";
        body.justifyContent = "center";
    }, []);

    return (
        <div className={style.container}>
            <div className={style.loginCard}>
                <div className={style.loginHeader}>
                    <h2>Welcome Back</h2>
                    <p>Please enter your credentials to login</p>
                </div>

                <form className={style.loginForm} autoComplete="off">
                    <div className={style.formGroup}>
                        <label htmlFor="username">Username</label>
                        <div className={style.inputGroup}>
                            <i className="fas fa-user" aria-hidden="true"></i>
                            <input type="text" id="username" name="username" placeholder="Enter your username" required />
                        </div>
                    </div>

                    <div className={style.formGroup}>
                        <label htmlFor="password">Password</label>
                        <div className={style.inputGroup}>
                            <i className="fas fa-lock" aria-hidden="true"></i>
                            <input type="password" id="password" name="password" placeholder="Enter your password" required />
                        </div>
                    </div>

                    <div className={style.formOptions}>
                        <div className={style.forgetPassword}>
                            <a href="pages/forgot-password.html" className={style.forgotPassword}>Forgot Password?</a>
                        </div>
                    </div>

                    <button type="submit" className={style.loginBtn}>Login</button>

                    <div className={style.registerLink}>
                        Don't have an account? <a href="pages/registration.html">Register</a>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default Login