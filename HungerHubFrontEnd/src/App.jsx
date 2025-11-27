import { Route, Routes } from 'react-router-dom'

import Login from './components/pages/login/Login'
import Registration from './components/pages/login/Registration'
import ForgotPassword from './components/pages/login/ForgotPassword'
import ResetPassword from './components/pages/login/ResetPassword'
import UserDashboard from './components/pages/user/UserDashboard'

function App() {
  return (
    <>
      <Routes>
        <Route path='/' element={<Login />} />
        <Route path='/login' element={<Login />} />
        <Route path='/register' element={<Registration />} />
        <Route path='/forgot-password' element={<ForgotPassword />} />
        <Route path='/reset-password' element={<ResetPassword />} />
        <Route path='/user' element={<UserDashboard />} />
      </Routes>
    </>
  )
}

export default App
