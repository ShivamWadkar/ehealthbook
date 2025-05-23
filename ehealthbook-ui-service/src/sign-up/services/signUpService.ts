import { z } from 'zod';
import interceptedAxios from '../../utils/interceptedAxios.ts';

export interface SignUpRequest {
  email: string;
  password: string;
  role: string;
}

const ZApiResponse = z.object({
  httpStatus: z.number(),
  error: z.boolean(),
  data: z.string()
});

export type ApiResponse = z.infer<typeof ZApiResponse>;

export async function signUpUser(
  signUpRequest: SignUpRequest,
  onSuccess: (successResponse: ApiResponse) => any,
  onFailure: (errorMessage: string, http_status: number) => any
): Promise<void> {
  const url = '/api/auth/signup';
  interceptedAxios
    .post(url, signUpRequest, {timeout: 15000})
    .then((response) => {
      console.log("Response: ", response)
      const parseResponse = ZApiResponse.safeParse(response.data)
      console.log("Parsed response: ", parseResponse)
      if (parseResponse.success) {
        onSuccess(parseResponse.data)
        // window.location.href = 'sign-in'
      } else {
        onFailure('Error occured while processing response', response.status)
      }
    })
  .catch((error: any) => {
    // Handle Axios error or network error
    const errorMessage = error.response ? error.response.data : error.message;
    const http_status = error.response ? error.response.status : 500; // Default to 500 if no response status
    onFailure(errorMessage, http_status);
  })
}
