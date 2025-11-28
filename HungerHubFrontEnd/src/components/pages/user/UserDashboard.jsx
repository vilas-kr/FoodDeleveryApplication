import { Navigation } from './UserNavigation';
import { useSelector } from 'react-redux';
import { useEffect, useRef, useState } from 'react'
import style from '../../css/user/userDashboard.module.css';

export default function UserDashboard() {

  useEffect(() => {
    const body = document.body.style;
    body.background = "rgba(245, 248, 250, 1)";
    body.display = "block";
  }, []);

  const user = useSelector((state) => state.user);
  const cuisinesRef = useRef(null);
  const [touchStartX, setTouchStartX] = useState(null);
  return (
    <>
      <Navigation />
      <main>
        <div className={style.popularCuisines}>
          <div className={style.title}>
            <span className={style.name}>{user.name ? user.name : ''}</span>
            <span className={style.message}>{user.name ? ',' : ''} What's on your mind?</span>
          </div>
          <div className={style.cuisinesWrap}>
            <button className={style.cuisineArrow} onClick={() => {
              if (!cuisinesRef.current) return;
              cuisinesRef.current.scrollBy({ left: -cuisinesRef.current.clientWidth, behavior: 'smooth' });
            }}>{'<'}</button>
            <div className={style.cuisines} ref={cuisinesRef}
              onTouchStart={(e) => setTouchStartX(e.touches[0].clientX)}
              onTouchEnd={(e) => {
                const endX = e.changedTouches[0].clientX;
                const start = touchStartX;
                if (!start) return;
                const delta = start - endX;
                const threshold = 40;
                if (delta > threshold) cuisinesRef.current.scrollBy({ left: cuisinesRef.current.clientWidth, behavior: 'smooth' });
                else if (delta < -threshold) cuisinesRef.current.scrollBy({ left: -cuisinesRef.current.clientWidth, behavior: 'smooth' });
                setTouchStartX(null);
              }}
            >
              <PopularCuisines />
            </div>
            <button className={style.cuisineArrow} onClick={() => {
              if (!cuisinesRef.current) return;
              cuisinesRef.current.scrollBy({ left: cuisinesRef.current.clientWidth, behavior: 'smooth' });
            }}>{'>'}</button>
          </div>
        </div>
      </main>
    </>
  )
}

const popularCuisines = [
  { name: 'Biryani', url: './popularCusines/Biryani.png' },
  { name: 'Biryani', url: './popularCusines/Biryani.png' }, 
  { name: 'Biryani', url: './popularCusines/Biryani.png' }, 
  { name: 'Biryani', url: './popularCusines/Biryani.png' }, 
  { name: 'Biryani', url: './popularCusines/Biryani.png' }, 
  { name: 'Biryani', url: './popularCusines/Biryani.png' }, 
  { name: 'Biryani', url: './popularCusines/Biryani.png' }, 
  { name: 'Biryani', url: './popularCusines/Biryani.png' }, 
  { name: 'Biryani', url: './popularCusines/Biryani.png' }, 
  { name: 'Biryani', url: './popularCusines/Biryani.png' }, 
  { name: 'Biryani', url: './popularCusines/Biryani.png' }, 
  { name: 'Biryani', url: './popularCusines/Biryani.png' }, 
  { name: 'Biryani', url: './popularCusines/Biryani.png' }, 
  { name: 'Biryani', url: './popularCusines/Biryani.png' }
];

export function PopularCuisines() {
  return (<>
    {popularCuisines.map((cuisine, index) => (
      <div key={index} className={style.cuisineCard}>
        <img src={cuisine.url} alt={cuisine.name} />
        <span>{cuisine.name}</span>
      </div>
    ))}
  </>)
}


