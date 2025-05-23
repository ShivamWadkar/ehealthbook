import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Checkbox from '@mui/material/Checkbox';
import CssBaseline from '@mui/material/CssBaseline';
import Divider from '@mui/material/Divider';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormLabel from '@mui/material/FormLabel';
import FormControl from '@mui/material/FormControl';
import Link from '@mui/material/Link';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import Stack from '@mui/material/Stack';
import MuiCard from '@mui/material/Card';
import { styled } from '@mui/material/styles';
import AppTheme from '../shared-theme/AppTheme.tsx';
import { GoogleIcon, FacebookIcon, SitemarkIcon } from './components/CustomIcons.tsx';
import { Radio, RadioGroup } from '@mui/material';
import { connect, ConnectedProps } from 'react-redux';
import { signUpAction } from './redux/actionCreator.ts';
import { SignUpRequest } from './services/signUpService.ts';
import { SignUpState } from './redux/signUpSlice.ts';
import { RootState } from '../store/index.ts';
import ApiFeedback from '../components/ApiFeedbackProps.tsx';
import { Link as RouterLink } from 'react-router-dom'

const Card = styled(MuiCard)(({ theme }) => ({
  display: 'flex',
  flexDirection: 'column',
  alignSelf: 'center',
  width: '100%',
  padding: theme.spacing(4),
  gap: theme.spacing(2),
  margin: 'auto',
  boxShadow:
    'hsla(220, 30%, 5%, 0.05) 0px 5px 15px 0px, hsla(220, 25%, 10%, 0.05) 0px 15px 35px -5px',
  [theme.breakpoints.up('sm')]: {
    width: '450px',
  },
  ...theme.applyStyles('dark', {
    boxShadow:
      'hsla(220, 30%, 5%, 0.5) 0px 5px 15px 0px, hsla(220, 25%, 10%, 0.08) 0px 15px 35px -5px',
  }),
}));

const SignUpContainer = styled(Stack)(({ theme }) => ({
  height: 'calc((1 - var(--template-frame-height, 0)) * 100%)',
  minHeight: '100%',
  padding: theme.spacing(2),
  [theme.breakpoints.up('sm')]: {
    padding: theme.spacing(4),
    paddingTop: theme.spacing(20),
  },
  '&::before': {
    content: '""',
    display: 'block',
    position: 'absolute',
    zIndex: -1,
    inset: 0,
    backgroundImage:
      'radial-gradient(ellipse at 50% 50%, hsl(210, 100%, 97%), hsl(0, 0%, 100%))',
    backgroundRepeat: 'no-repeat',
    ...theme.applyStyles('dark', {
      backgroundImage:
        'radial-gradient(at 50% 50%, hsla(210, 86.30%, 25.70%, 0.50), hsl(220, 30%, 5%))',
    }),
  },
}));

const SignUp = (props: PropsFromRedux): React.JSX.Element => {
  const [email, setEmail] = React.useState('');
  const [emailError, setEmailError] = React.useState(false);
  const [emailErrorMessage, setEmailErrorMessage] = React.useState('');
  const [password, setPassword] = React.useState('');
  const [passwordError, setPasswordError] = React.useState(false);
  const [passwordErrorMessage, setPasswordErrorMessage] = React.useState('');
  const [profileType, setProfileType] = React.useState('');
  const [profileTypeError, setProfileTypeError] = React.useState(false);
  const [profileTypeErrorMessage, setProfileTypeErrorMessage] = React.useState('');
  const [showFeedback, setShowFeedback] = React.useState(false);

  const validateInputs = () => {
    let isValid = true;
    if (!email || !/\S+@\S+\.\S+/.test(email)) {
      setEmailError(true);
      setEmailErrorMessage('Please enter a valid email address.');
      isValid = false;
    } else {
      setEmailError(false);
      setEmailErrorMessage('');
    }

    if (!password || password.length < 8) {
      setPasswordError(true);
      setPasswordErrorMessage('Password must be at least 8 characters long.');
      isValid = false;
    } else {
      setPasswordError(false);
      setPasswordErrorMessage('');
    }

    if (!profileType) {
      setProfileTypeError(true);
      setProfileTypeErrorMessage('Please select your profile type as Patient / Doctor.');
      isValid = false;
    } else {
      setProfileTypeError(false);
      setProfileTypeErrorMessage('');
    }

    return isValid;
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    if (emailError || passwordError || profileTypeError) {
      return;
    }
    const userData = {email: email, password: password, role: profileType}
    props.signUpAction(userData)
    setShowFeedback(true);
  };

  const disableShowFeedback = () => {
    setShowFeedback(false);
  }

  return (
    <AppTheme {...props}>
      <CssBaseline enableColorScheme />
      <SignUpContainer direction="column" justifyContent="space-between">
        <Card variant="outlined">
          <SitemarkIcon />
          <Typography
            component="h1"
            variant="h4"
            sx={{ width: '100%', fontSize: 'clamp(2rem, 10vw, 2.15rem)' }}
          >
            Sign up
          </Typography>
          <Box
            component="form"
            onSubmit={handleSubmit}
            sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}
          >
            <FormControl>
              <FormLabel htmlFor="email" sx={{textAlign: 'left'}}>Email</FormLabel>
              <TextField
                required
                fullWidth
                id="email"
                placeholder="your@email.com"
                name="email"
                autoComplete="email"
                variant="outlined"
                error={emailError}
                helperText={emailErrorMessage}
                color={emailError ? 'error' : 'primary'}
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
            </FormControl>
            <FormControl>
              <FormLabel htmlFor="password" sx={{textAlign: 'left'}}>Password</FormLabel>
              <TextField
                required
                fullWidth
                name="password"
                placeholder="••••••"
                type="password"
                id="password"
                autoComplete="new-password"
                variant="outlined"
                error={passwordError}
                helperText={passwordErrorMessage}
                color={passwordError ? 'error' : 'primary'}
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </FormControl>
            <FormControl>
              <FormLabel htmlFor="profileType" sx={{textAlign: 'left'}}>Sign Up As</FormLabel>
              <RadioGroup
                row
                value={profileType}
                onChange={(e) => {
                  setProfileType(e.target.value)
                  if (e.target.value) {
                    setProfileTypeError(false);
                    setProfileTypeErrorMessage('');
                  }
                }}
              >
                <FormControlLabel value="PATIENT" control={<Radio />} label="Patient" />
                <FormControlLabel value="DOCTOR" control={<Radio />} label="Doctor" />
              </RadioGroup>
              {profileTypeError && (
                <Typography color="error" variant="caption" sx={{ mt: 1 }}>
                  {profileTypeErrorMessage}
                </Typography>
              )}
            </FormControl>
            <FormControlLabel
              control={<Checkbox value="allowExtraEmails" color="primary" />}
              label="I want to receive updates via email."
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              onClick={validateInputs}
              disabled={props.signUp.isLoading}
            >
              Sign up
            </Button>
          </Box>
          <Divider>
            <Typography sx={{ color: 'text.secondary' }}>or</Typography>
          </Divider>
          <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
            <Button
              fullWidth
              variant="outlined"
              onClick={() => alert('Sign up with Google')}
              startIcon={<GoogleIcon />}
            >
              Sign up with Google
            </Button>
            <Button
              fullWidth
              variant="outlined"
              onClick={() => alert('Sign up with Facebook')}
              startIcon={<FacebookIcon />}
            >
              Sign up with Facebook
            </Button>
            <Typography sx={{ textAlign: 'center' }}>
              Already have an account?{' '}
              <Link
                component={RouterLink}
                to="/sign-in"
                variant="body2"
                sx={{ alignSelf: 'center' }}
              >
                Sign in
              </Link>
            </Typography>
            {
              showFeedback &&
              <ApiFeedback
                isLoading={props.signUp.isLoading}
                httpStatus={props.signUp.httpStatus}
                error={props.signUp.error}
                onClose={disableShowFeedback}
                routeTo='/sign-in'
              />
            }
          </Box>
        </Card>
      </SignUpContainer>
    </AppTheme>
  );
}

interface DispatchToProps {
  signUpAction: (signUpRequest: SignUpRequest) => void
}

const mapDispatchToProps = (dispatch: any) : DispatchToProps => ({
  signUpAction: (signUpRequest: SignUpRequest) => dispatch(signUpAction(signUpRequest))
})

interface StateToProps {
  signUp: SignUpState
}

const mapStateToProps = (state: RootState): StateToProps => ({
  signUp: state.signUp
})

const connector = connect(mapStateToProps, mapDispatchToProps)
type PropsFromRedux = ConnectedProps<typeof connector>

export default connector(SignUp)