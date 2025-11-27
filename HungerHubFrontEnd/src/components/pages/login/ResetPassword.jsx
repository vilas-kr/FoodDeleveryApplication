import React, { useEffect, useState } from 'react';
import styles from '../../css/login/resetPassword.module.css';
import { useSelector } from 'react-redux';
import axios from 'axios';
import url from '../endpoint'
import { useNavigate } from 'react-router-dom';

export default function ResetPassword() {

  const username = useSelector((state) => state.user.userName) || '';
  const [newPassword, setNewPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [checks, setChecks] = useState({ length: false, uppercase: false, lowercase: false, number: false, special: false });
  
  const navigate = useNavigate();
  useEffect(() => {
    validate(newPassword);
  }, [newPassword]);

  function validate(pw) {
    setChecks({
      length: pw.length >= 8,
      uppercase: /[A-Z]/.test(pw),
      lowercase: /[a-z]/.test(pw),
      number: /[0-9]/.test(pw),
      special: /[!@#$%^&*(),.?"':{}|<>\[\]\\\/]/.test(pw),
    });
  }

  function isValid() {
    return Object.values(checks).every(Boolean) && newPassword === confirmPassword;
  }

  function togglePassword(id) {
    const el = document.getElementById(id);
    if (!el) return;
    el.type = el.type === 'password' ? 'text' : 'password';
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!isValid()) {
      alert('Please ensure password meets requirements and both fields match.');
      return;
    }

    //API call to reset password
    axios.patch(`${url}/user`, {
        userName: username,
        password: newPassword
    })
    .then((response) => {
        alert("Password updated successfully. Please login with your new password.");
        navigate("/login");
    }).catch((error) => {
        console.log(error);
        alert('Error updating password. Please try again later.');
    });
  };

  return (
    <div className={styles.container}>
      <div className={styles.resetPasswordCard}>
        <div className={styles.resetPasswordHeader}>
          <h2>Reset Password</h2>
          <p>Please create a new password</p>
        </div>

        <form className={styles.resetPasswordForm} onSubmit={handleSubmit} autoComplete="off">
          <div className={styles.formGroup}>
            <label>Username</label>
            <div className={styles.inputGroup}>
              <i className={`fas fa-user ${styles.icon}`} />
              <input id="username" value={username} readOnly className={`${styles.input} ${styles.disabledInput}`} />
            </div>
          </div>

          <div className={styles.formGroup}>
            <label>New Password</label>
            <div className={styles.inputGroup}>
              <i className={`fas fa-lock ${styles.icon}`} />
              <input id="new-password" type="password" value={newPassword} onChange={(e) => setNewPassword(e.target.value)} placeholder="Enter new password" required className={styles.input} />
              <button type="button" className={styles.togglePassword} onClick={() => togglePassword('new-password')}>
                <i className="fas fa-eye"></i>
              </button>
            </div>
          </div>

          <div className={styles.formGroup}>
            <label>Confirm New Password</label>
            <div className={styles.inputGroup}>
              <i className={`fas fa-lock ${styles.icon}`} />
              <input id="confirm-password" type="password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} placeholder="Confirm new password" required className={styles.input} />
              <button type="button" className={styles.togglePassword} onClick={() => togglePassword('confirm-password')}>
                <i className="fas fa-eye"></i>
              </button>
            </div>
          </div>

          <div className={styles.passwordRequirements}>
            <p>Password must contain:</p>
            <ul>
              <li className={checks.length ? styles.valid : ''}><i className="fas fa-circle" /> At least 8 characters</li>
              <li className={checks.uppercase ? styles.valid : ''}><i className="fas fa-circle" /> At least one uppercase letter</li>
              <li className={checks.lowercase ? styles.valid : ''}><i className="fas fa-circle" /> At least one lowercase letter</li>
              <li className={checks.number ? styles.valid : ''}><i className="fas fa-circle" /> At least one number</li>
              <li className={checks.special ? styles.valid : ''}><i className="fas fa-circle" /> At least one special character</li>
            </ul>
          </div>

          <button type="submit" className={styles.updateBtn}>Update Password</button>

          <div className={styles.backToLogin}>
            <a href="/"> <i className="fas fa-arrow-left"></i> Back to Login</a>
          </div>
        </form>
      </div>
    </div>
  );
}
