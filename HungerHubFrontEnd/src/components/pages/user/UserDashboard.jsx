import { Navigation } from './UserNavigation';
import { useSelector } from 'react-redux';
import { use, useEffect, useRef, useState } from 'react'
import style from '../../css/user/userDashboard.module.css';
import { Link } from 'react-router-dom';
import axios from 'axios';
import url from '../endpoint';


export default function UserDashboard() {

  useEffect(() => {
    const body = document.body.style;
    body.background = "rgba(245, 248, 250, 1)";
    body.display = "block";
  }, []);

  const user = useSelector((state) => state.user);
  const scrollRef = useRef(null);

  const leftScroll = () => {
    scrollRef.current.scrollBy({ left: -1000, behavior: 'smooth' });
  }

  const rightScroll = () => {
    scrollRef.current.scrollBy({ left: 1000, behavior: 'smooth' });
  }

  return (
    <>
      <Navigation />
      <main>
        <div className={style.popularCuisines}>
          <div className={style.title}>
            <div>
              <span className={style.name}>{user.name ? user.name : ''}</span>
              <span className={style.message}>{user.name ? ',' : ''} What's on your mind?</span>
            </div>
            <div>
              <button type='button' className={style.button} onClick={leftScroll}>
                <i className='fas fa-caret-left' />
              </button>
              <button type='button' className={style.button} onClick={rightScroll}>
                <i className='fas fa-caret-right' />
              </button>
            </div>
          </div>
          <div className={style.cuisines} ref={scrollRef}>
            <PopularCuisines />
          </div>
        </div>

        <div className={style.horizontalLine}>
          <hr className={style.line} />
        </div>

        <div className={style.popularRestaurants}>
          <div className={style.sectionTitle}>
            <h2>Popular Restaurants</h2>
          </div>
          <div className={style.restaurants}>
            <PopularRestaurants />
          </div>
        </div>
      </main>
    </>
  )
}

const popularCuisines = [
  { name: 'Biryani', url: './popularCusines/Biryani1.avif' },
  { name: 'burger', url: './popularCusines/burger.avif' },
  { name: 'cake', url: './popularCusines/cake.avif' },
  { name: 'chinese', url: './popularCusines/chinese.avif' },
  { name: 'chole_bhuture', url: './popularCusines/chole_bhature.avif' },
  { name: 'dosa', url: './popularCusines/dosa.avif' },
  { name: 'icecream', url: './popularCusines/icecream.avif' },
  { name: 'kebab', url: './popularCusines/kebabs.avif' },
  { name: 'khichdi', url: './popularCusines/khichdi.avif' },
  { name: 'noodles', url: './popularCusines/noodles.avif' },
  { name: 'north indian', url: './popularCusines/north_indian.avif' },
  { name: 'paratha', url: './popularCusines/paratha.avif' },
  { name: 'pasta', url: './popularCusines/pasta.avif' },
  { name: 'pastry', url: './popularCusines/pastry.avif' },
  { name: 'pizza', url: './popularCusines/pizzas.avif' },
  { name: 'roll', url: './popularCusines/rolls.avif' },
  { name: 'salad', url: './popularCusines/salad.avif' },
  { name: 'shake', url: './popularCusines/shake.avif' },
  { name: 'shawarma', url: './popularCusines/shawarma.avif' },
  { name: 'south indian', url: './popularCusines/south_indian.avif' },
];

export function PopularCuisines() {
  return (<>
    {popularCuisines.map((cuisine, index) => (
      <div key={index} className={style.cuisineCard}>
        <Link to={`/user/search/${cuisine.name.toLowerCase()}`}>
          <img src={cuisine.url} alt={cuisine.name} />
        </Link>
      </div>
    ))}
  </>)
}

export function PopularRestaurants() {
  const [restaurants, setRestaurants] = useState([]);
  useEffect(() => {
    axios.get(`${url}/restaurant/popular`)
      .then((response) => {
        setRestaurants(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error('There was an error fetching the popular restaurants!', error);
      })
  }, []);
  return (<>
    {restaurants.map((restaurant) => (
      <div key={restaurant.id} className={style.restaurantCard}>
        <Link to={`/user/restaurant/${restaurant.id}`}>
          <img src={`${url}/restaurant/image/${restaurant.image}`} alt={restaurant.name} className={style.restaurantImage} />
          <div className={style.restaurantInfo}>
            <h3 className={style.restaurantName}>{restaurant.name}</h3>
            <div className={style.restaurantRating}>
              <div>
                <img src="./icons/star_icon.png" alt="rating" className={style.starIcon} />
                <span>{restaurant.rating}</span>
              </div>
              <div>
                <span className={style.cookingTime}>{restaurant.cookingTime} - {restaurant.cookingTime + 5} mins</span>
              </div>
            </div>
            <div className={style.restaurantCuisine}>
              {restaurant.cuisines.map((cuisine, index) => (
                <span key={index} className={style.cuisineItem}>{cuisine}, </span>
              ))}
            </div>
            <div className={style.restaurantLocation}>
              {restaurant.address}
            </div>
          </div>
        </Link>
      </div>
    ))}
  </>)
}


