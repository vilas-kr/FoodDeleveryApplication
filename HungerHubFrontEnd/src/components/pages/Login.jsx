import { useEffect, useState } from "react"
import axios from 'axios'
import { useDispatch } from 'react-redux'
import { Link, useNavigate } from 'react-router-dom'

import style from '../css/login.module.css'
import url from '../pages/endpoint'
import { addUser } from "../../redux/userSlice"
function Login() {
    
    useEffect(() => {
        const body = document.body.style;

        body.background = "linear-gradient(135deg, #667eea, #764ba2)";
        body.minHeight = "100vh";
        body.display = "flex";
        body.alignItems = "center";
        body.justifyContent = "center";
    }, []);

    const [formData, setFormData] = useState({});
    const [login, setLogin] = useState(true);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const handleChange = (event) => {
        setFormData({
            ...formData,
            [event.target.name] : event.target.value
        });
    };

    const verifyUser = (e) => {
        e.preventDefault();
        setLogin(false);
        axios.post(`${url}/user/login`,{
            userName : formData.username,
            password : formData.password 
        })
        .then((response) => {
            dispatch(addUser({
                userId : response.data.userId,
                role : response.data.role
            }));
            switch(response.data.role){
                case 'USER' : 
                    navigate("/user")
                    break
                case 'RESTAURANT' : 
                    navigate("/restaurant")
                    break
                case 'SUPERADMIN' :
                    navigate("/superadmin")
                    break
                case 'DELIVERYAGENT' :
                    navigate("/delivery")
                    break
                default :
                    alert("Invalid Role")
            }
        })
        .catch((error) =>{
            if(error.response){
                if(error.response.status === 400) alert("Bad Request")
                else if(error.response.status === 404) alert(error.response.data.message)
                else if(error.response.status >= 500) alert("Server Error Contact Admin")
            }else{
                console.log(error)
                alert("Network Error")
            }
        })
        .finally((response) =>{
            setLogin(true)
        }); 
    }

    return (
        <div className={style.container}>
            <div className={style.loginCard}>
                <div className={style.loginHeader}>
                    <h2>Welcome Back</h2>
                    <p>Please enter your credentials to login</p>
                </div>

                <form className={style.loginForm} autoComplete="off" onSubmit={(e) => verifyUser(e)}>
                    <div className={style.formGroup}>
                        <label htmlFor="username">Username</label>
                        <div className={style.inputGroup}>
                            <i className="fas fa-user" aria-hidden="true"></i>
                            <input type="text" id="username" name="username" onChange={(event) => handleChange(event)} placeholder="Enter your username" required />
                        </div>
                    </div>

                    <div className={style.formGroup}>
                        <label htmlFor="password">Password</label>
                        <div className={style.inputGroup}>
                            <i className="fas fa-lock" aria-hidden="true"></i>
                            <input type="password" id="password" name="password" onChange={(event) => handleChange(event)} placeholder="Enter your password" required />
                        </div>
                    </div>

                    <div className={style.formOptions}>
                        <div className={style.forgetPassword}>
                            <Link to="/forgot-password">Forgot Password?</Link>
                        </div>
                    </div>

                    <button type="submit" className={style.loginBtn} disabled = {!login}>{login ? "Login" : "Verifying..."}</button>

                    <div className={style.registerLink}>
                        Don't have an account? <Link to="/register">Register here</Link>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default Login