import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import style from '../../css/user/navigation.module.css';   


export function Navigation() {
        
    const user = useSelector((state) => state.user);
    return (
        <header>
        <div className={style.container}>
            <div className={style.leftGroup}>
                <div className={style.image}>
                    <img src="./logo.png" alt="company logo" />
                </div>
                <div className={style.appName}>
                    <h2>HungerHub</h2>
                </div>  
                <div className={style.address}>
                    <span>Deliver to</span> 
                    <div className={style.location}>
                        <input type="text" placeholder="Set your address" value={user.address} disabled/>
                    </div>
                </div>
            </div>  

            <div className={style.navigationLinks}>
                <div className={style.search}>
                    <Link to="/user/search">
                        <i className="fas fa-search"/>
                        <span>Search</span>
                    </Link>
                </div>
                <div className={style.help}>
                    <Link to="/help">
                        <i className="fas fa-question-circle"/>
                        <span>Help</span>   
                    </Link>
                </div>
                <div className={style.profile}>     
                    <Link to="/user/profile">
                        <i className="fas fa-user"/>
                        <span>{user.name}</span>
                    </Link>
                </div>
                <div className={style.cart}>
                    <Link to="/user/cart">
                        <i className="fas fa-shopping-cart"/>
                        <span>Cart</span>
                    </Link>
                </div>
            </div>
        </div>
        </header>
    )
}