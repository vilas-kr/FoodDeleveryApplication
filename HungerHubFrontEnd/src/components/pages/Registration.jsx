import React, { useState, useEffect } from 'react';
import styles from '../css/registration.module.css';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';

import url from '../pages/endpoint';

export default function Registration() {
  const [form, setForm] = useState({
    name: '',
    username: '',
    email: '',
    phone: '',
    password: '',
    confirmPassword: '',
    address: '',
    role: 'USER',
  });

  const [pwValid, setPwValid] = useState({
    length: false,
    uppercase: false,
    lowercase: false,
    number: false,
    special: false,
  });

  const [showRequirements, setShowRequirements] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    validatePassword(form.password);
  }, []);

  function validatePassword(pw) {
    const checks = {
      length: pw.length >= 8,
      uppercase: /[A-Z]/.test(pw),
      lowercase: /[a-z]/.test(pw),
      number: /[0-9]/.test(pw),
      special: /[!@#$%^&*(),.?"':{}|<>\[\]\\\/]/.test(pw),
    };
    setPwValid(checks);
  }

  function isPasswordValid() {
    return Object.values(pwValid).every(Boolean);
  }

  function togglePassword(id) {
    const el = document.getElementById(id);
    if (!el) return;
    el.type = el.type === 'password' ? 'text' : 'password';
  }

  function formatPhone(val) {
    const digits = val.replace(/\D/g, '').slice(0, 10);
    if (digits.length <= 5) return digits;
    return digits.slice(0, 5) + ' ' + digits.slice(5);
  }

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name === 'phone') {
      setForm((s) => ({ ...s, phone: formatPhone(value) }));
      return;
    }
    setForm((s) => ({ ...s, [name]: value }));
    if (name === 'password') {
      validatePassword(value);
      setShowRequirements(true);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!isPasswordValid()) {
      setShowRequirements(true);
      alert('Password does not meet the required conditions.');
      return;
    }

    if (form.password !== form.confirmPassword) {
      alert('Passwords do not match.');
      return;
    }

    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (!emailRegex.test(form.email)) {
      alert('Please enter a valid email address.');
      return;
    }

    const phoneRegex = /^[6-9]{1}[0-9]{4}\s[0-9]{5}$/;
    if (!phoneRegex.test(form.phone)) {
      alert('Please enter a valid phone number in the format XXXXX XXXXX (first digit 6-9).');
      return;
    }

    //API call to register user 
    axios.post(`${url}/user`, {
        name: form.name,
        userName: form.username,
        email: form.email,
        phoneNumber: form.phone.replace(/\D/g, '').slice(0, 10),
        password: form.password,
        address: form.address,
        role: form.role,
      })
        .then((response) => {
            alert('Registration successful! Please login to continue...');
            navigate('/');
        })
        .catch((error) => {
            if (error.response) {
                if (error.response.status === 400) alert('Bad Request');
                else if (error.response.status === 404) alert(error.response.data.message);
                else if (error.response.status >= 500) alert('Server Error Contact Admin');
                } else {
                console.log(error);
                alert('Network Error');
            }
        });
  };

  return (
    <div className={styles.container}>
      <div className={styles.registerCard}>
        <div className={styles.registerHeader}>
          <h2>Create Account</h2>
          <p>Please fill in the details to register</p>
        </div>

        <form className={styles.registerForm} onSubmit={handleSubmit} autoComplete="off">
          <div className={styles.formRow}>
            <div className={styles.formGroup}>
              <label>Full Name</label>
              <div className={styles.inputGroup}>
                <i className={`fas fa-user ${styles.icon}`} />
                <input
                  id="name"
                  name="name"
                  value={form.name}
                  onChange={handleChange}
                  placeholder="Enter your full name"
                  required
                  className={styles.input}
                  autoComplete='new-name'
                />
              </div>
            </div>

            <div className={styles.formGroup}>
              <label>Username</label>
              <div className={styles.inputGroup}>
                <i className={`fas fa-at ${styles.icon}`} />
                <input
                  id="username"
                  name="username"
                  value={form.username}
                  onChange={handleChange}
                  placeholder="Choose a username"
                  required
                  className={styles.input}
                />
              </div>
            </div>
          </div>

          <div className={styles.formRow}>
            <div className={styles.formGroup}>
              <label>Email Address</label>
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
              <small className={styles.hint}>Format: example@domain.com</small>
            </div>

            <div className={styles.formGroup}>
              <label>Phone Number</label>
              <div className={styles.inputGroup}>
                <i className={`fas fa-phone ${styles.icon}`} />
                <input
                  id="phone"
                  name="phone"
                  value={form.phone}
                  onChange={handleChange}
                  placeholder="Enter your phone number (e.g., 996-456-7890)"
                  required
                  className={styles.input}
                  autoComplete='new-phone'
                />
              </div>
              <small className={styles.hint}>Format: XXXXX XXXXX</small>
            </div>
          </div>

          <div className={styles.formRow}>
            <div className={styles.formGroup}>
              <label>Password</label>
              <div className={styles.inputGroup}>
                <i className={`fas fa-lock ${styles.icon}`} />
                <input
                  id="password"
                  name="password"
                  type="password"
                  value={form.password}
                  onChange={handleChange}
                  placeholder="Create a password"
                  required
                  className={styles.input}
                />
                <button type="button" className={styles.togglePassword} onClick={() => togglePassword('password')}>
                  <i className="fas fa-eye" />
                </button>
              </div>
            </div>

            <div className={styles.formGroup}>
              <label>Confirm Password</label>
              <div className={styles.inputGroup}>
                <i className={`fas fa-lock ${styles.icon}`} />
                <input
                  id="confirmPassword"
                  name="confirmPassword"
                  type="password"
                  value={form.confirmPassword}
                  onChange={handleChange}
                  placeholder="Confirm your password"
                  required
                  className={styles.input}
                />
                <button type="button" className={styles.togglePassword} onClick={() => togglePassword('confirmPassword')}>
                  <i className="fas fa-eye" />
                </button>
              </div>
            </div>
          </div>

          <div className={`${styles.passwordRequirements} ${showRequirements ? styles.show : ''}`}>
            <p>Password must contain:</p>
            <ul>
              <li className={pwValid.length ? styles.valid : ''}>At least 8 characters</li>
              <li className={pwValid.uppercase ? styles.valid : ''}>At least one uppercase letter</li>
              <li className={pwValid.lowercase ? styles.valid : ''}>At least one lowercase letter</li>
              <li className={pwValid.number ? styles.valid : ''}>At least one number</li>
              <li className={pwValid.special ? styles.valid : ''}>At least one special character</li>
            </ul>
          </div>

          <div className={styles.formGroup}>
            <label>Address</label>
            <div className={styles.inputGroup}>
              <i className={`fas fa-map-marker-alt ${styles.icon}`} />
              <textarea
                id="address"
                name="address"
                value={form.address}
                onChange={handleChange}
                placeholder="Enter your address"
                required
                className={styles.textarea}
                autoComplete='new-address'
              />
            </div>
          </div>

          <div className={styles.formGroup}>
            <label>Select Role</label>
            <div className={styles.inputGroup}>
              <i className={`fas fa-user-tag ${styles.icon}`} />
              <select name="role" value={form.role} onChange={handleChange} required className={styles.select}>
                <option value="USER" >Customer</option>
                <option value="RESTAURANT">Restaurant Admin</option>
                <option value="DELIVERYAGENT">Delivery Agent</option>
                <option value="SUPERADMIN">Super Admin</option>
              </select>
            </div>
          </div>

          <button type="submit" className={styles.registerBtn}>Create Account</button>

          <div className={styles.loginLink}>
            Already have an account? <Link to="/login">Login</Link>
          </div>
        </form>
      </div>
    </div>
  );
}
