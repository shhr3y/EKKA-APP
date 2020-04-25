


//listen for auth status changes

auth.onAuthStateChanged(user => {
    if(user != null){
//          var creation = user.metadata.creationTime;
//          var lastSignIn = user.metadata.lastSignInTime;
//
//
//          if(creation == lastSignIn){
//            console.log("new user");
//            console.log(creation,lastSignIn);
//            container.classList.remove("right-panel-active");
//            //JavascriptInterface.toast("Now login with the same details.");
//          }
//          else{
//            console.log("existing user");

            JavascriptInterface.toast("Login Successful!!");
            JavascriptInterface.openMain(user.uid);
//          }
    }
    else{
          console.log("not logged In")
    }
})

const direct = document.querySelector('#directSignIn');
direct.addEventListener('click',(e) => {
    JavascriptInterface.openMain(rUI9DDRiQOZLtk4hDCI96srHVea2);
});

//logout
//const logout = document.querySelector('#lgout');
//logout.addEventListener('click',(e) => {
//    e.preventDefault();
//    auth.signOut();
//});

//signup
const signupForm = document.querySelector('#signup-form');
const signinForm = document.querySelector('#signin-form');

signupForm.addEventListener('submit',(e) => {

    e.preventDefault();
    //get user Info
    const name = signupForm['up_name'].value;
    const email = signupForm['up_email'].value;
    const password = signupForm['up_password'].value;

    //signup the user
    auth.createUserWithEmailAndPassword(email,password).then(cred => {
        return db.collection('users').doc(cred.user.uid).set({
            name: signupForm['up_name'].value,
            email: signupForm['up_email'].value,
            password: signupForm['up_password'].value
        })
    }).then(() => {
         signupForm.reset();
    });
});


signinForm.addEventListener('submit',(e) => {

    e.preventDefault();
    //get user Info
    const email = signinForm['in_email'].value;
    const password = signinForm['in_password'].value;

    //signin the user
        auth.signInWithEmailAndPassword(email,password).then(cred => {
                signinForm.reset();
        });
});


auth.setPersistence(firebase.auth.Auth.Persistence.NONE)