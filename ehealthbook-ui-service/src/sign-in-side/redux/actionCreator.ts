import { signInUser, SignInRequest } from '../services/signInService.ts';
import { signInErrorAction, signInLoadingAction, signInUpdateAction } from './signInSlice.ts';

// Action creator to sign up a user
export const signInAction = (signUpRequest: SignInRequest) => async (dispatch: any): Promise<void> => {
  dispatch(signInLoadingAction())
  void signInUser(
    signUpRequest,
    (apiResponse) => {
      dispatch(
        signInUpdateAction({
          data: apiResponse
        })
      )
    },
    (errorMessage, httpStatus) => {
      dispatch(signInErrorAction({ errorMessage, httpStatus}))
    }
  )
}
