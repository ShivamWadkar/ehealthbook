import React from "react";
import { Snackbar, Alert, CircularProgress, Box } from "@mui/material";
import { useNavigate } from 'react-router-dom';

interface ApiFeedbackProps {
  isLoading: boolean;
  httpStatus: number | null | undefined;
  error: any;
  onClose: () => void;
  routeTo?: string 
}

const ApiFeedback: React.FC<ApiFeedbackProps> = ({
  isLoading,
  httpStatus,
  error,
  onClose,
  routeTo
}) => {
  const navigate = useNavigate();
  
  React.useEffect(() => {
    if (routeTo && (httpStatus === 200 || httpStatus === 201)) {
      navigate(`${routeTo}`);
    }

    if (routeTo && (httpStatus === 401)) {
      navigate("/sign-in");
    }
  }, [httpStatus, navigate, routeTo]);

  if (isLoading) {
    return (
      <Box
      position="fixed"
      top={0}
      left={0}
      width="100vw"
      height="100vh"
      zIndex={1300}
      display="flex"
      justifyContent="center"
      alignItems="center"
      sx={{
        backdropFilter: 'blur(4px)',
        backgroundColor: 'rgba(0, 0, 0, 0.2)',
        pointerEvents: 'auto',
      }}
    >
      <CircularProgress />
    </Box>
    );
  }

  if (httpStatus === null || error === null) {
    return;
  }

  const open = httpStatus !== null || !!error;

  let severity: "success" | "warning" | "error" = "error";
  let message = error.data || "Something went wrong.";

  switch (httpStatus) {
    case 200:
      severity = "success";
      message = `${message}`;
      break;
    case 201:
      severity = "success";
      message = `${message}`;
      break;
    case 401:
      severity = "warning";
      message = `${message}`;
      break;
    case 403:
      severity = "error";
      message = `${message}`;
      break;
    default:
      if (httpStatus && httpStatus >= 400) {
        severity = "error";
        message = `${error.data}`;
      }
      break;
  }

  return (
    <Snackbar
      open={open}
      autoHideDuration={3000}
      onClose={onClose}
      anchorOrigin={{ vertical: "top", horizontal: "center" }}
    >
      <Alert
        onClose={onClose}
        severity={severity}
        variant="filled"
        sx={{ width: "100%" }}
      >
        {message}
      </Alert>
    </Snackbar>
  );
};

export default ApiFeedback;
