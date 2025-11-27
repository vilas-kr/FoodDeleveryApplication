import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import style from '../../css/user/navigation.module.css';   
import { useEffect } from "react";

export function Navigation() {

    const user = useSelector((state) => state.user) || {};
    useEffect(() => {
        const body = document.body.style;
        body.background = "rgba(195, 199, 203, 1)";
        body.display = "block";
    }, []);

        
    return (
        <div className={style.container}>
            <div className={style.leftGroup}>
                <div className={style.image}>
                    <img src="./logo.png" alt="company logo" />
                </div>
                <div className={style.appName}>
                    <h2>HungerHub</h2>
                </div>  
                <div className={style.address}>
                    <spam>Deliver to</spam> 
                    <div className={style.location}>
                        <input type="text" placeholder="Set your address" value={user.address} disabled/>
                    </div>
                </div>
            </div>  

            <div className={style.navigationLinks}>
                <div className={style.search}>
                    <Link to="/user/search">
                        <i className="fas fa-search"/>
                        <spam>Search</spam>
                    </Link>
                </div>
                <div className={style.help}>
                    <Link to="/help">
                        <i className="fas fa-question-circle"/>
                        <spam>Help</spam>   
                    </Link>
                </div>
                <div className={style.profile}>     
                    <Link to="/user/profile">
                        <i className="fas fa-user"/>
                        <spam>{user.name}</spam>
                    </Link>
                </div>
                <div className={style.cart}>
                    <Link to="/user/cart">
                        <i className="fas fa-shopping-cart"/>
                        <spam>Cart</spam>
                    </Link>
                </div>
            </div>
        </div>
    )
}