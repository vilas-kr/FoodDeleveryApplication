import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import {Link, useNavigate} from 'react-router-dom';
import styles from '../css/forgotPassword.module.css';
import url from '../pages/endpoint'
import axios from 'axios';
import { addUser } from '../../redux/userSlice';

export default function ForgotPassword() {
  const [form, setForm] = useState({ username: '', email: '' });
  const navigation = useNavigate();
  const dispatch = useDispatch();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((s) => ({ ...s, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (!form.username.trim()) {
      alert('Please enter your username.');
      return;
    }
    if (!emailRegex.test(form.email)) {
      alert('Please enter a valid email address.');
      return;
    }

    axios.get(`${url}/user/email`, {
        params: {
            userName : form.username
        }
    })
    .then((response) => {
        if(response.data == form.email){
            dispatch(addUser({ userName : form.username}));
            navigation("/reset-password");
        }else{
            alert("Username and Email do not match");
        }
    })
    .catch((error) => {
        if (error.response) {
            if (error.response.status === 404) alert('User not found.');
            else if (error.response.status >= 500) alert('Server Error Contact Admin');
        } else {
            console.log(error);
            alert('Network Error');
        }
    });
    
  };

  return (
    <div className={styles.container}>
      <div className={styles.forgotPasswordCard}>
        <div className={styles.forgotPasswordHeader}>
          <h2>Forgot Password</h2>
          <p>Enter your username and email to reset your password</p>
        </div>

        <form className={styles.forgotPasswordForm} onSubmit={(e) => handleSubmit(e)} autoComplete="off">
          <div className={styles.formGroup}>
            <label htmlFor="username">Username</label>
            <div className={styles.inputGroup}>
              <i className={`fas fa-user ${styles.icon}`} />
              <input
                id="username"
                name="username"
                value={form.username}
                onChange={handleChange}
                placeholder="Enter your username"
                required
                className={styles.input}
              />
            </div>
          </div>

          <div className={styles.formGroup}>
            <label htmlFor="email">Email Address</label>
            <div className={styles.inputGroup}>
              <i className={`fas fa-envelope ${styles.icon}`} />
              <input
                id="email"
                name="email"
                type="email"
                value={form.email}
                onChange={handleChange}
                placeholder="Enter your email address"
                required
                className={styles.input}
              />
            </div>
          </div>

          <button type="submit" className={styles.resetBtn}>Reset Password</button>

          <div className={styles.backToLogin}>
            <Link to="/login"> <i className="fas fa-arrow-left"></i> Back to Login</Link>
          </div>
        </form>
      </div>
    </div>
  );
}
