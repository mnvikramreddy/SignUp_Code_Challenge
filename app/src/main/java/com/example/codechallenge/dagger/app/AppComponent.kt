import android.content.Context
import com.example.codechallenge.dagger.app.AppModule
import com.example.codechallenge.dagger.signin.SignInComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun signInComponent(): SignInComponent.Factory
}

@Module(
    subcomponents = [SignInComponent::class]
)
object SubcomponentModule
